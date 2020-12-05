package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.data.HomeRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


class ViewModelFactory @Inject constructor(
    private val repository: HomeRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass != ViewModelFactory::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return ViewModelFactory(
            repository
        ) as T
    }
}