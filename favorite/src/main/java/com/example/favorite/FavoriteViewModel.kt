package com.example.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.core.data.HomeRepository

class FavoriteViewModel @ViewModelInject constructor(private val repository: HomeRepository) :
    ViewModel() {
    val favoriteGame = LiveDataReactiveStreams.fromPublisher(repository.getFavorite())
}