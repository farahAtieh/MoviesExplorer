package com.example.moviesexplorer.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.moviesexplorer.data.model.Movie

@Composable
fun SectionItem(navController: NavController, title: String, sectionData: LazyPagingItems<Movie>?) {
    Text(
        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
        fontFamily = FontFamily.Default,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        text = title
    )
    MovieCarousel(navController, movies = sectionData)
}

@Composable
fun MovieCarousel(navController: NavController, movies: LazyPagingItems<Movie>?) {

    if (movies != null) {
        LazyRow(Modifier.padding(bottom = 10.dp)) {
            items(movies) { movie ->
                MovieItem(navController, movie = movie)
            }
            when (movies.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(color = Color.Black)
                    }
                }

                else -> {}
            }
        }
    }
}

