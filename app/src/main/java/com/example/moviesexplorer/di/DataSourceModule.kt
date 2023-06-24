package com.example.moviesexplorer.di

import com.example.moviesexplorer.data.datasources.RemoteDataSource
import com.example.moviesexplorer.data.datasources.RemoteDataSourceImp
import com.example.moviesexplorer.network.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideRemoteDataSource(
        moviesApiService: MoviesApiService,
    ): RemoteDataSource = RemoteDataSourceImp(moviesApiService)
}