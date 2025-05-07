package tmg.aboutthisapp.utils

import android.content.Context
import android.util.Log
import tmg.aboutthisapp.configuration.License

object PlayServiceLicenseUtils {

    private const val fileName = "third_party_licenses"
    private const val fileNameMetadata = "third_party_license_metadata"

    fun readLicenses(context: Context): List<License> {
        try {
            val file = context.readRawResourceAsString(context.getRawId(fileName))
            val fileMetadata = context.readRawResourceAsString(context.getRawId(fileNameMetadata))

            return fileMetadata
                .trim()
                .split("\n")
                .mapNotNull { line ->
                    val firstSpace = line.indexOfFirst { it == ' ' }
                    val range = line.take(firstSpace)
                    val label = line.drop(firstSpace).trim()

                    val indexes = range.split(":")
                    if (indexes.size != 2) {
                        return@mapNotNull null
                    }
                    val startIndex = indexes.first().toIntOrNull() ?: return@mapNotNull null
                    val length = indexes.last().toIntOrNull() ?: return@mapNotNull null

                    val content = try {
                        file.substring(startIndex, startIndex + length).trim()
                    } catch (e: StringIndexOutOfBoundsException) {
                        // Something went wrong, omit the license
                        Log.e("AboutThisApp", "License $label has been omitted as license info not found (${e.message})")
                        return@mapNotNull label to "Unknown license"
                    }

                    return@mapNotNull label to content
                }
                .distinctBy { it.first }
                .sortedBy { it.first.lowercase() }
                .map { (label, content) ->
                    if (content.startsWith("http://")) {
                        content.replace("http://", "https://")
                    }
                    if (content.startsWith("https://")) {
                        return@map License.Url(label, content)
                    } else {
                        return@map License.Text(label, content)
                    }
                }
        } catch (e: Exception) {
            // Do nothing if format cannot be read
            e.printStackTrace()
            return emptyList()
        }
    }

    private fun Context.readRawResourceAsString(id: Int): String {
        val inputStream = this.resources.openRawResource(id)
        val byteArray = ByteArray(inputStream.available())
        inputStream.read(byteArray)
        return String(byteArray)
    }

    private fun Context.getRawId(name: String): Int {
        return this.resources.getIdentifier(name, "raw", this.packageName)
    }
}