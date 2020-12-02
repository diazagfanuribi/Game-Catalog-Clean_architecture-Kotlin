package com.example.game_catalog_clean_architecture_kotlin.core.domain.repository

import androidx.paging.PagingData
import com.example.game_catalog_clean_architecture_kotlin.core.data.Resource
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.Game
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.GameDeveloperModel
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.GameList
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    fun getGames() : Flowable<PagingData<GameList>>

    fun getDeveloper() : Flowable<Resource<List<GameDeveloperModel>>>

    fun getFavorite(): Flowable<List<Game>>

    fun setFavorite(game: Game, state: Boolean)
}