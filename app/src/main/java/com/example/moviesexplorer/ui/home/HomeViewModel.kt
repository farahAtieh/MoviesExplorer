package com.example.moviesexplorer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesexplorer.data.model.Movie
import com.example.moviesexplorer.data.model.MovieCategory
import com.example.moviesexplorer.data.model.State
import com.example.moviesexplorer.data.usecases.FetchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(State.LOADING)
    val state: StateFlow<State> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    var popularMoviesState: Flow<PagingData<Movie>>? = null
    var topRatedMoviesState: Flow<PagingData<Movie>>? = null
    var topRevenuesMoviesState: Flow<PagingData<Movie>>? = null

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        viewModelScope.launch {

            try {
                val (popularDeferred, topRatedDeferred, topRevenuesDeferred) = awaitAll(
                    async { fetchMoviesUseCase.invoke(MovieCategory.POPULAR) },
                    async { fetchMoviesUseCase.invoke(MovieCategory.TOP_RATED) },
                    async { fetchMoviesUseCase.invoke(MovieCategory.REVENUES) }
                )
                popularMoviesState = popularDeferred.cachedIn(viewModelScope)
                topRatedMoviesState = topRatedDeferred.cachedIn(viewModelScope)
                topRevenuesMoviesState = topRevenuesDeferred.cachedIn(viewModelScope)

            } catch (e: Exception) {
//                _state.value = State.ERROR
            }

        }
    }
}