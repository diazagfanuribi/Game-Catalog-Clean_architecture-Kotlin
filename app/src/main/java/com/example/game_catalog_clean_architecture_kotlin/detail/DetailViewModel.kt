package com.example.game_catalog_clean_architecture_kotlin.detail

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.usecase.HomeUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel @ViewModelInject constructor(
    private val homeUseCase: HomeUseCase, @Assisted savedStateHandle: SavedStateHandle
) : BaseDetailViewModel(savedStateHandle) {

    private val mDisposable = CompositeDisposable()

    private val args: DetailFragmentArgs by navArgs()

    private val _gameDetail = MutableLiveData<Resource<List<Game>>>()

    val gameDetail: LiveData<Resource<List<Game>>>
        get() = _gameDetail

    init {
        getGameDetail()

    }

    fun getGameDetail() {
        mDisposable.add(
            homeUseCase.getGameById(args.game.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _gameDetail.postValue(it)
                })
    }

    fun updateFavorite(game: Game, isFavorite: Boolean) =
        mDisposable.add(
            homeUseCase.setFavorite(game, isFavorite)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { Log.d("Favorite error", it.message.toString()) }
                .doOnComplete {
                    Log.d("Favorite done", "done")
                }
                .subscribe()
        )


    override fun onCleared() {
        mDisposable.dispose()
        super.onCleared()
    }
}