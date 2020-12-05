package com.example.game_catalog_clean_architecture_kotlin.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.example.core.data.Resource
import com.example.core.domain.model.GameDeveloperModel
import com.example.core.domain.model.GameList
import com.example.core.domain.usecase.HomeUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class HomeViewModel @ViewModelInject constructor(val homeUseCase: HomeUseCase) : ViewModel() {
    fun getGames(): Flowable<PagingData<GameList>> = homeUseCase.getGames()
        .map { pagingData ->
            pagingData.filter {
                it.id != null
            }
        }
        .cachedIn(viewModelScope)

    val developer: LiveData<Resource<List<GameDeveloperModel>>>
        get() = LiveDataReactiveStreams.fromPublisher(homeUseCase.getDeveloper())

    fun retry(): LiveData<Resource<List<GameDeveloperModel>>> {
        return LiveDataReactiveStreams.fromPublisher(homeUseCase.getDeveloper())
    }

}