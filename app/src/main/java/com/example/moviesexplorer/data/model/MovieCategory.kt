package com.example.moviesexplorer.data.model

enum class MovieCategory(val title: String, val category: String) {
    POPULAR("Popular","popularity.desc"),
    TOP_RATED("Top Rated","vote_count.desc"),
    REVENUES("Revenues","revenue.desc"),
}