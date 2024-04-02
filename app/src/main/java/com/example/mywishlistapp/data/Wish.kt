package com.example.mywishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishes-table")
data class Wish (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish_title")
    val title: String = "",
    @ColumnInfo(name = "wish_description")
    val description: String = "",
)

object DummyWishes{
    val wishes = listOf(
        Wish(1, "New Laptop", "I need a new laptop for my work"),
        Wish(2, "New Phone", "I need a new phone for my personal use"),
        Wish(3, "New Watch", "I need a new watch for my daily use"),
        Wish(4, "New Shoes", "I need a new shoes for my daily use"),
        Wish(5, "New Bag", "I need a new bag for my daily use"),
        Wish(6, "New Shirt", "I need a new shirt for my daily use"),
        Wish(7, "New Jeans", "I need a new jeans for my daily use"),
        Wish(8, "New T-Shirt", "I need a new t-shirt for my daily use"),
        Wish(9, "New Cap", "I need a new cap for my daily use"),
        Wish(10, "New Socks", "I need a new socks for my daily use"),
    )
}