package com.example.embeddedLogger.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.embeddedLogger.DB.Store
import com.example.embeddedLogger.DB.StoreDao
import com.example.embeddedLogger.DB.StoreDatabse
import kotlinx.coroutines.launch

class AddViewModel(application: Application) : AndroidViewModel(application) {
    private val storeDao: StoreDao = StoreDatabse.getInstance(application).storeDao

    fun insertItem(name: String, category: String, price: String) {
        viewModelScope.launch {
            val newItem = Store(0, name, category, price.toDouble()) // Assuming `id` is auto-generated
            storeDao.insertAnime(newItem)  // Insert into the database asynchronously
        }
    }
}
