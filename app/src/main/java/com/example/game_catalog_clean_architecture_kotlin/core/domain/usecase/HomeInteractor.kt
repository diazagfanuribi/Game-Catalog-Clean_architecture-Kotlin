package com.example.game_catalog_clean_architecture_kotlin.core.domain.usecase

import com.bumptech.glide.load.engine.Resource
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeInteractor @Inject constructor() : HomeUseCase {
    override fun getGames(): Flow<Resource<List<Game>>> {
        TODO("Not yet implemented")
    }

    override fun getDeveloper(): Flow<Resource<List<Game>>> {
        TODO("Not yet implemented")
    }
}