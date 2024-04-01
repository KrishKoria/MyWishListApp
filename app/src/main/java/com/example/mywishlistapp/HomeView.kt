package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeView() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBar(title = "My Wish List", onBackNavClicked = {
                Toast.makeText(context, "Back Button Clicked", Toast.LENGTH_SHORT).show()
            })
        }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

        }
    }
}