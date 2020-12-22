package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListGameResponses
import com.example.core.data.source.remote.response.ListGameDeveloperResponses
import com.example.core.data.source.remote.response.GameDetailResponse
import dagger.Provides
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    fun getGames(
        @Query("ordering") ordering: String = "-rating",
        @Query("page") page: Int,
        @Query("per_page") perPage: Int

    ): Flowable<ListGameResponses>

    @GET("games/{id}")
    fun getGamesDetail(
        @Path("id") id: Int
    ): Flowable<GameDetailResponse>

    @GET("developers")
    fun getDeveloper(
    ): Flowable<ListGameDeveloperResponses>
}