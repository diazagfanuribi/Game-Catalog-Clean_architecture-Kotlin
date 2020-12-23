package com.example.favorite.detail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.core.data.HomeRepository
import com.example.core.domain.model.Game
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailFavoritViewModel @ViewModelInject constructor(private val repository: HomeRepository) : ViewModel(){
    private val mDisposable = CompositeDisposable()
    fun updateFavorite(game: Game, isFavorite: Boolean) =
        mDisposable.add(
            repository.setFavorite(game, isFavorite)
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