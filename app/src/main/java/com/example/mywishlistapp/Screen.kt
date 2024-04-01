package com.example.mywishlistapp

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home")
    data object AddDetailScreen : Screen("add_detail")
}