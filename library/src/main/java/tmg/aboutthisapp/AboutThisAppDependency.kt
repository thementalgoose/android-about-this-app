package tmg.aboutthisapp

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

data class AboutThisAppDependency(
    val order: Int,
    val dependencyName: String,
    val author: String,
    val imageUrl: String = "",
    @DrawableRes
    val imageRes: Int = 0,
    val url: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(order)
        parcel.writeString(dependencyName)
        parcel.writeString(author)
        parcel.writeString(imageUrl)
        parcel.writeInt(imageRes)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return dependencyName.hashCode() + author.hashCode() + imageUrl.hashCode() + url.hashCode() + imageRes.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<AboutThisAppDependency> {
        override fun createFromParcel(parcel: Parcel): AboutThisAppDependency {
            return AboutThisAppDependency(parcel)
        }

        override fun newArray(size: Int): Array<AboutThisAppDependency?> {
            return arrayOfNulls(size)
        }
    }
}