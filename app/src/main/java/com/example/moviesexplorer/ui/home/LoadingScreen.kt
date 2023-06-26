package com.example.moviesexplorer.ui.home

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.LoadingScreen(){
    Spacer(modifier = Modifier.weight(1f))
    CircularProgressIndicator(
        Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(modifier = Modifier.weight(1f))
}