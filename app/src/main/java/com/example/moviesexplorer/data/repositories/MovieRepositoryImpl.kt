package com.example.moviesexplorer.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.moviesexplorer.network.MoviesApiService
import com.example.moviesexplorer.pagination.MoviesPagingSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesApiService: MoviesApiService,
) : MovieRepository {

    override fun fetchMoviesByCategory(sortBy: String) = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            MoviesPagingSource(moviesApiService, sortBy)
        }
    ).flow
}