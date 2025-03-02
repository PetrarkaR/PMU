package com.example.embeddedLogger.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StoreDao {
    @Query("SELECT * FROM store_table")
    fun getAllAnime(): LiveData<List<Store>>

    @Insert
    suspend fun insertAnime(anime: Store)

    @Query("DELETE FROM store_table")
    suspend fun clearAllItems()
}