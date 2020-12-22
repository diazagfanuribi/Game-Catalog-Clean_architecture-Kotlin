package com.example.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.example.core.data.HomeRepository
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameList
import io.reactivex.Flowable

class FavoriteViewModel @ViewModelInject constructor(private val repository: HomeRepository) : ViewModel(){
    val favoriteGame = LiveDataReactiveStreams.fromPublisher(repository.getFavorite())
}