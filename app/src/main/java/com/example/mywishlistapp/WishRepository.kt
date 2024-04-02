package com.example.mywishlistapp

import com.example.mywishlistapp.data.Wish
import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDAO: WishDAO){
    fun getWishes() = wishDAO.getWishes()
    suspend fun addWish(wish: Wish) {
        wishDAO.addWish(wish)
    }

    suspend fun updateWish(wish: Wish) {
        wishDAO.updateWish(wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDAO.deleteWish(wish)
    }

    fun getWishById(id: Long) : Flow<Wish> = wishDAO.getWishById(id)
}