package com.example.moviesexplorer.data.model

interface ErrorGettable {

    fun getErrorIfExist(): String?
}

sealed class LoadState<out T> : ErrorGettable {
    object Loading : LoadState<Nothing>()
    class Loaded<T>(val value: T) : LoadState<T>()
    class Error<T>(val e: String) : LoadState<T>()

    val isLoading get() = this is Loading
    override fun getErrorIfExist(): String? = if (this is Error) e else null
    fun getValueOrNull(): T? = if (this is Loaded<T>) value else null
}