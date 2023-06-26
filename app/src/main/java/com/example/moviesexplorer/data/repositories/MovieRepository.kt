package com.example.moviesexplorer.data.repositories

import androidx.paging.PagingData
import com.example.moviesexplorer.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun fetchMoviesByCategory(sortBy: String): Flow<PagingData<Movie>>
}