package com.example.moviesexplorer.data.datasources

import com.example.moviesexplorer.data.model.MovieResponse

interface RemoteDataSource {

    suspend fun getMoviesBy(sortBy: String?): MovieResponse
}