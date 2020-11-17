package com.example.game_catalog_clean_architecture_kotlin.core.domain.repository

import com.bumptech.glide.load.engine.Resource
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    fun getGames() : Flow<Resource<List<Game>>>

    fun getDeveloper() : Flow<Resource<List<Game>>>
}