package com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote

import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.network.ApiResponse
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.network.ApiService
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.GameDetailResponse
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.GameDeveloperResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getGames(page : Int, perPage : Int) = apiService.getGames(page = page,perPage = perPage)

    fun getDeveloper(): Flowable<ApiResponse<List<GameDeveloperResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<GameDeveloperResponse>>>()

        val client = apiService.getDeveloper()

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({response->
                val dataArray = response.results
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else
                    ApiResponse.Empty)
            },{error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
            })
        return resultData.toFlowable(BackpressureStrategy.LATEST)
    }

    fun getGamesDetail(id : String) : Flowable<ApiResponse<GameDetailResponse>>{
        val resultData = PublishSubject.create<ApiResponse<GameDetailResponse>>()

        val client = apiService.getGamesDetail(id)

        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({response->
                val dataArray = response
                resultData.onNext(ApiResponse.Success(dataArray))
            },{error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
            })
        return resultData.toFlowable(BackpressureStrategy.LATEST)
    }
}