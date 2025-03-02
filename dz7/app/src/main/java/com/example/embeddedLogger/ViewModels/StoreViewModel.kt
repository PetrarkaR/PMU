package com.example.embeddedLogger.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.embeddedLogger.DB.Store
import com.example.embeddedLogger.DB.StoreDao
import com.example.embeddedLogger.DB.StoreDatabse
import kotlinx.coroutines.launch

class StoreViewModel(application: Application) : AndroidViewModel(application) {
    private val storeDao: StoreDao = StoreDatabse.getInstance(application).storeDao
    val allItems: LiveData<List<Store>> = storeDao.getAllAnime()
    fun clearAllItems() {
        viewModelScope.launch {
            storeDao.clearAllItems()  // Calling the DAO method to clear all records
        }
    }
}