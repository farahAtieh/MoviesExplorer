package com.example.moviesexplorer.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviesexplorer.data.model.Movie
import com.example.moviesexplorer.data.model.MovieCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val sectionsMap = getSectionForEachCategory(viewModel = viewModel)

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(bottom = 10.dp),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "CATALOG",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color.Black
                ),

                )
        }, content = { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                // combine their load states and check if any of them is still loading until all three have been fetched
                val isLoading = sectionsMap.any {
                    it.value?.loadState?.refresh is LoadState.Loading
                }

                // combine their error states
                val isError = sectionsMap.any {
                    it.value?.loadState?.refresh is LoadState.Error
                }

                if (isLoading) {
                    LoadingScreen()
                } else if (isError) {
                    ErrorScreen()
                } else {
                    sectionsMap.forEach { section ->
                        SectionItem(navController, section.key, section.value)
                    }
                }
            }

        }
    )
}


@Composable
private fun getSectionForEachCategory(viewModel: HomeViewModel): Map<String, LazyPagingItems<Movie>?> {
    val sectionsMap = mutableMapOf<String, LazyPagingItems<Movie>?>()
    sectionsMap[MovieCategory.POPULAR.title] =
        viewModel.popularMoviesState?.collectAsLazyPagingItems()
    sectionsMap[MovieCategory.TOP_RATED.title] =
        viewModel.topRatedMoviesState?.collectAsLazyPagingItems()
    sectionsMap[MovieCategory.REVENUES.title] =
        viewModel.topRevenuesMoviesState?.collectAsLazyPagingItems()

    return sectionsMap
}