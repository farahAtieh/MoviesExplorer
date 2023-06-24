package com.example.moviesexplorer.data.datasources

import com.example.moviesexplorer.network.MoviesApiService
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val moviesApiService: MoviesApiService,
) : RemoteDataSource {

    override suspend fun getMoviesBy(sortBy: String?) =
        moviesApiService.getMoviesBy(sortBy)
}