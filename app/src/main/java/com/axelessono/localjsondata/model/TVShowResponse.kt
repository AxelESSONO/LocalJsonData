package com.axelessono.localjsondata.model

import com.google.gson.annotations.SerializedName

data class TVShowResponse(

    @SerializedName("total")
    var total: String,

    @SerializedName("pages")
    var pages: Int,

    @SerializedName("page")
    var page: Int,

    @SerializedName("tv_shows")
    var tvShows: ArrayList<TVShow>) {
}