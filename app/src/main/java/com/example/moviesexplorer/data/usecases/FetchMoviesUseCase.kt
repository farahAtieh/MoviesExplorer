package com.example.moviesexplorer.data.usecases

import androidx.paging.PagingData
import com.example.moviesexplorer.data.model.Movie
import com.example.moviesexplorer.data.model.MovieCategory
import com.example.moviesexplorer.data.repositories.MovieRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class FetchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {

    fun invoke(category: MovieCategory): Flow<PagingData<Movie>> =
        repository.fetchMoviesByCategory(category.category)
}