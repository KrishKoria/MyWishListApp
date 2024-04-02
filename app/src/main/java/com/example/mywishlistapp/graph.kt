package com.example.mywishlistapp

import android.content.Context
import androidx.room.Room
import com.example.mywishlistapp.data.WishDatabase
import com.example.mywishlistapp.data.WishRepository

object Graph {
    private lateinit var database: WishDatabase
    val wishRepository by lazy { WishRepository(database.wishDAO()) }

    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context,
            WishDatabase::class.java,
            "wish_database.db"
        ).build()
    }
}