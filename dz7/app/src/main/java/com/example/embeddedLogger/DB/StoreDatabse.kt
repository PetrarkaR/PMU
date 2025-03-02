package com.example.embeddedLogger.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Store::class], version = 1, exportSchema = false)
abstract class StoreDatabse : RoomDatabase() {
    abstract val storeDao: StoreDao

    companion object {
        @Volatile
        private var INSTANCE: StoreDatabse? = null
        fun getInstance(context: Context): StoreDatabse {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StoreDatabse::class.java,
                        "store_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}