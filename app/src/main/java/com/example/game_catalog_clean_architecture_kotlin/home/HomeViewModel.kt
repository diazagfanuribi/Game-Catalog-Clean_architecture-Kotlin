package com.example.game_catalog_clean_architecture_kotlin.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.example.game_catalog_clean_architecture_kotlin.core.data.Resource
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.GameDeveloperModel
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.GameList
import com.example.game_catalog_clean_architecture_kotlin.core.domain.usecase.HomeUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class HomeViewModel @Inject constructor(val homeUseCase: HomeUseCase) : ViewModel() {
    fun getGames(): Flowable<PagingData<GameList>> = homeUseCase.getGames()
        .map { pagingData ->
            pagingData.filter {
                it.id != null
            }
        }
        .cachedIn(viewModelScope)


    val developer: LiveData<Resource<List<GameDeveloperModel>>> = LiveDataReactiveStreams.fromPublisher(homeUseCase.getDeveloper())



}