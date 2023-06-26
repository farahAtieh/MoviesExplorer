package com.example.moviesexplorer.util

import android.os.Bundle
import androidx.navigation.NavType
import com.example.moviesexplorer.data.model.Movie
import com.google.gson.Gson

/**
 *  This code was inspired by David-Legend solution
 *  */
abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = true) {

    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}

class MovieArgType : JsonNavType<Movie>() {

    override fun fromJsonParse(value: String): Movie = Gson().fromJson(value, Movie::class.java)
    override fun Movie.getJsonParse(): String = Gson().toJson(this)
}

