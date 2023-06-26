package com.example.moviesexplorer.di

import com.example.moviesexplorer.data.repositories.MovieRepository
import com.example.moviesexplorer.data.repositories.MovieRepositoryImpl
import com.example.moviesexplorer.network.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieRepository(moviesApiService: MoviesApiService): MovieRepository =
        MovieRepositoryImpl(moviesApiService)

}