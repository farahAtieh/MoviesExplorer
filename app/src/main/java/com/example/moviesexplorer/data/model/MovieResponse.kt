package com.example.moviesexplorer.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: String,
    @SerializedName("results")
    val movies: List<Movie>,
)