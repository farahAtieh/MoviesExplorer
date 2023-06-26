package com.example.moviesexplorer.data.model

import android.net.Uri
import com.example.moviesexplorer.BuildConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Long,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("poster_path")
    val imageUrl: String,
    @SerializedName("vote_average")
    val rate: Float
)  {
    fun getImageFullUrl(path: String?, size: String = "w500") =
        "${BuildConfig.IMAGE_BASE_URL}${size}/${path}"

    override fun toString(): String = Uri.encode(Gson().toJson(this))
}