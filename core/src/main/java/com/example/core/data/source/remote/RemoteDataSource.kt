package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.GameDetailResponse
import com.example.core.data.source.remote.response.GameDeveloperResponse
import com.example.core.data.source.remote.response.GameResponse
import com.example.core.data.source.remote.response.ListGameResponses
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getGames(page : Int, perPage : Int): Flowable<ApiResponse<List<GameResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<GameResponse>>>()

        val client = apiService.getGames(page = 1, perPage = 10)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
            })
        return resultData.toFlowable(BackpressureStrategy.LATEST)
    }

    fun getDeveloper(): Flowable<ApiResponse<List<GameDeveloperResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<GameDeveloperResponse>>>()

        val client = apiService.getDeveloper()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
            })
        return resultData.toFlowable(BackpressureStrategy.LATEST)
    }

    fun getGamesDetail(id : Int) : Flowable<ApiResponse<GameDetailResponse>>{
        val resultData = PublishSubject.create<ApiResponse<GameDetailResponse>>()

        val client = apiService.getGamesDetail(id=id)

        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({response->
                val dataArray = response
                resultData.onNext(if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            },{error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
            })
        return resultData.toFlowable(BackpressureStrategy.LATEST)
    }
}

