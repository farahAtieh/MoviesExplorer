package com.example.moviesexplorer.util

import com.example.moviesexplorer.data.model.Movie

const val MOVIE_DETAIL_ROUTE = "movieDetail"
const val HOME_ROUTE = "homeScreen"
const val MOVIE_DETAIL_ARGUMENT = "movie"

sealed class Screen(val route: String) {
    object MovieDetailScreen : Screen(route = "$MOVIE_DETAIL_ROUTE/{$MOVIE_DETAIL_ARGUMENT}") {

        fun passMovie(movie: Movie): String {
            val data = movie.toString()
            return "$MOVIE_DETAIL_ROUTE/$data"
        }
    }

    object HomeRouteScreen : Screen(route = HOME_ROUTE)
}