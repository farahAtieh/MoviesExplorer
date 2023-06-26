package com.example.moviesexplorer.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesexplorer.data.model.Movie
import com.example.moviesexplorer.util.Screen
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun MovieItem(navController: NavController, movie: Movie?) {
    movie?.let {
        Image(
            modifier = Modifier
                .padding(start = 10.dp)
                .height(200.dp)
                .clickable {
                    navController.navigate(
                        route = Screen.MovieDetailScreen.passMovie(movie)
                    )
                },
            painter = rememberCoilPainter(
                request = movie.getImageFullUrl(movie.imageUrl)
            ),
            contentDescription = movie.title
        )
    }
}