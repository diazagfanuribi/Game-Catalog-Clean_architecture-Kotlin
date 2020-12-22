package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.data.HomeRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


class FavoriteViewModelFactory @Inject constructor(
    private val repository: HomeRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass != FavoriteViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return FavoriteViewModel(
            repository
        ) as T
    }
}