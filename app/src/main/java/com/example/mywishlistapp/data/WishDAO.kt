package com.example.mywishlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mywishlistapp.data.Wish
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wish: Wish)
    @Query("SELECT * FROM `wishes-table`")
    abstract fun getWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(wish: Wish)

    @Delete
    abstract suspend fun deleteWish(wish: Wish)

    @Query("SELECT * FROM `wishes-table` WHERE id = :id")
    abstract fun getWishById(id: Long): Flow<Wish>
}