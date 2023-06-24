package com.example.moviesexplorer.data.model

data class MovieResponse(
    val page: String,
    val results: List<Movie>,
)