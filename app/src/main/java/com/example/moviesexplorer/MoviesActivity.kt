package com.example.moviesexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.moviesexplorer.ui.theme.MoviesExplorerTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviesexplorer.data.model.Movie
import com.example.moviesexplorer.ui.moviedetail.MovieDetailScreen
import com.example.moviesexplorer.ui.home.HomeScreen
import com.example.moviesexplorer.util.MOVIE_DETAIL_ARGUMENT
import com.example.moviesexplorer.util.MovieArgType
import com.example.moviesexplorer.util.Screen
import com.google.gson.Gson

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesExplorerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeRouteScreen.route
                    ) {
                        composable(
                            Screen.HomeRouteScreen.route,
                        ) {
                            HomeScreen(navController = navController)
                        }

                        composable(
                            route = Screen.MovieDetailScreen.route,
                            arguments = listOf(navArgument(MOVIE_DETAIL_ARGUMENT) {
                                type = MovieArgType()
                            })
                        ) { backStackEntry ->
                            val movie = backStackEntry.arguments?.getString(MOVIE_DETAIL_ARGUMENT)
                                .let { Gson().fromJson(it, Movie::class.java) }
                            MovieDetailScreen(
                                navController = navController,
                                movie = movie
                            )
                        }
                    }
                }
            }
        }
    }
}