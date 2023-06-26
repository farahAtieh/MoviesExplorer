package com.example.moviesexplorer.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesexplorer.data.model.Movie
import com.example.moviesexplorer.network.MoviesApiService

class MoviesPagingSource(
    private val moviesApiService: MoviesApiService,
    private val sortBy: String,
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition((anchorPosition))?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> =
        try {
            val page = params.key ?: 1
            val response = moviesApiService.getMoviesBy(page = page, sortBy)
            LoadResult.Page(
                data = response.movies,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.movies.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}