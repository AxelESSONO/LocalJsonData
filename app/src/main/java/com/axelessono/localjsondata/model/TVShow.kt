package com.axelessono.localjsondata.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TVShow(
    @SerializedName("id")
    val id : Int,

    @SerializedName("name")
    val name : String?,

    @SerializedName("permalink")
    val permalink : String?,

    @SerializedName("start_date")
    val startDate : String?,

    @SerializedName("end_date")
    val endDate : String?,

    @SerializedName("country")
    val country : String?,

    @SerializedName("network")
    val network : String?,

    @SerializedName("status")
    val status : String?,

    @SerializedName("image_thumbnail_path")
    val imageThumbnailPath : String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(permalink)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeString(country)
        parcel.writeString(network)
        parcel.writeString(status)
        parcel.writeString(imageThumbnailPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TVShow> {
        override fun createFromParcel(parcel: Parcel): TVShow {
            return TVShow(parcel)
        }

        override fun newArray(size: Int): Array<TVShow?> {
            return arrayOfNulls(size)
        }
    }
}