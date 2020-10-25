package tmg.components.about

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.annotation.DrawableRes
import tmg.components.BuildConfig
import tmg.components.utils.marketUri

data class AboutThisAppConfiguration(
    val isDarkMode: Boolean,
    val name: String,
    val nameDesc: String,
    val imageUrl: String? = null,
    @DrawableRes
    val imageRes: Int? = null,
    val github: String? = null,
    val email: String? = null,
    val website: String? = null,
    private val appPackageName: String? = null,
    private val play: String? = null,
    val appName: String,
    val appVersion: String,
    val subtitle: String? = null,
    val footnote: String? = null,
    val insetsForNavigationBar: Boolean = false,
    var dependencies: List<AboutThisAppDependency>
): Parcelable {

    init {
        if (play == null && appPackageName == null) {
            throw RuntimeException("Please provide either an appPackageName or a play store URL")
        }
        else if (play != null && appPackageName != null) {
            Log.e("Components", "You have provided a package name and a play store link. The play store URL will be used")
        }
    }

    val playStore: String
        get() {
            return if (play == null) {
                marketUri.format(appPackageName)
            } else if (appPackageName != null) {
                play
            } else {
                throw RuntimeException("Please provide either an appPackageName or a play store URL")
            }
        }

    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString(),
        (parcel.readByte() != 0.toByte()),
        emptyList()
    ) {
        val dependencyList: List<AboutThisAppDependency> = mutableListOf()
        parcel.readList(dependencyList, AboutThisAppDependency::class.java.classLoader)
        this.dependencies = dependencyList
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeByte(if (isDarkMode) 1 else 0)
        p0?.writeString(name)
        p0?.writeString(nameDesc)
        p0?.writeString(imageUrl)
        p0?.writeInt(imageRes ?: 0)
        p0?.writeString(github)
        p0?.writeString(email)
        p0?.writeString(website)
        p0?.writeString(appPackageName)
        p0?.writeString(play)
        p0?.writeString(appName)
        p0?.writeString(appVersion)
        p0?.writeString(subtitle)
        p0?.writeString(footnote)
        p0?.writeByte(if (insetsForNavigationBar) 1 else 0)
        p0?.writeList(dependencies)
    }

    override fun describeContents(): Int {
        return isDarkMode.hashCode() +
            name.hashCode() +
            nameDesc.hashCode() +
            imageUrl.hashCode() +
            (imageRes ?: 0).hashCode() +
            github.hashCode() +
            email.hashCode() +
            website.hashCode() +
            appPackageName.hashCode() +
            play.hashCode() +
            dependencies.toTypedArray().hashCode() +
            appName.hashCode() +
            appVersion.hashCode() +
            footnote.hashCode() +
            subtitle.hashCode() +
            insetsForNavigationBar.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<AboutThisAppConfiguration> {
        override fun createFromParcel(parcel: Parcel): AboutThisAppConfiguration {
            return AboutThisAppConfiguration(parcel)
        }

        override fun newArray(size: Int): Array<AboutThisAppConfiguration?> {
            return arrayOfNulls(size)
        }
    }

}