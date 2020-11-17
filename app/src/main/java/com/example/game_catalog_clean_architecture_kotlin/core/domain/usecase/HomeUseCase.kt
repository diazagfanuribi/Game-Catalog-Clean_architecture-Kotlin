package com.example.game_catalog_clean_architecture_kotlin.core.domain.usecase

import com.bumptech.glide.load.engine.Resource
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    fun getGames() : Flow<Resource<List<Game>>>

    fun getDeveloper() : Flow<Resource<List<Game>>>
}