package com.example.game_catalog_clean_architecture_kotlin.core.data

import com.bumptech.glide.load.engine.Resource
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.Game
import com.example.game_catalog_clean_architecture_kotlin.core.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor() : IHomeRepository {
    override fun getGames(): Flow<Resource<List<Game>>> {
        TODO("Not yet implemented")
    }

    override fun getDeveloper(): Flow<Resource<List<Game>>> {
        TODO("Not yet implemented")
    }

}