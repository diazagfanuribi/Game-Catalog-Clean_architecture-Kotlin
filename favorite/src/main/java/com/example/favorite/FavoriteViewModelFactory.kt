package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.data.HomeRepository
import com.example.favorite.detail.DetailFavoritViewModel
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

class DetailFavoritViewModelFactory @Inject constructor(
    private val repository: HomeRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass != DetailFavoritViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return DetailFavoritViewModel(
            repository
        ) as T
    }
}