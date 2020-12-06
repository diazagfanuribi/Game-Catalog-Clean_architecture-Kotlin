package com.example.game_catalog_clean_architecture_kotlin.detail

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.usecase.HomeUseCase
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel @ViewModelInject constructor(
    val homeUseCase: HomeUseCase, @Assisted savedStateHandle: SavedStateHandle
) : BaseDetailViewModel(savedStateHandle) {

    private val mDisposable = CompositeDisposable()

    private val args: DetailFragmentArgs by navArgs()

    private val _gameDetail = MutableLiveData<Resource<Game>>()

    val gameDetail: LiveData<Resource<Game>>
        get() = LiveDataReactiveStreams.fromPublisher(homeUseCase.getGameById(args.game.id))
//    init {
//        mDisposable.add(
//            homeUseCase.getGameById(args.game.id)
//            .subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{
//             _gameDetail.postValue(it)
//         })
//
//    }

    fun updateFavorite(game: Game, isFavorite: Boolean) =
        mDisposable.add(
            homeUseCase.setFavorite(game, isFavorite)
                .subscribeOn(Schedulers.io())
                .subscribe()
        )


    override fun onCleared() {
        mDisposable.dispose()
        super.onCleared()
    }
}