package com.example.moviesexplorer.network

import com.example.moviesexplorer.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("discover/movie")
    suspend fun getMoviesBy(@Query("sort_by") sortBy: String?): MovieResponse
}