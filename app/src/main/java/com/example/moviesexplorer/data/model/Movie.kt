package com.example.moviesexplorer.data.model

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
)