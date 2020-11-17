package com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.network

import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.GameDetailResponse
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.ListGameResponses
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames
            (@Query("ordering") ordering : String = "-rating",
             @Query("page") page: Int,
             @Query("per_page") perPage: Int
    ): ListGameResponses

    @GET("games/{id}")
    suspend fun getGamesDetail(@Path("id") id : String): GameDetailResponse
}