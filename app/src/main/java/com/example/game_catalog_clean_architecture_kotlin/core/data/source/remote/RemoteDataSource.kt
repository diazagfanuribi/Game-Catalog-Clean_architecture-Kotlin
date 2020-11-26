package com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote

import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.network.ApiService

class RemoteDataSource(private val apiService: ApiService) {
    fun getGames(page : Int, perPage : Int) = apiService.getGames(page = page,perPage = perPage)
}