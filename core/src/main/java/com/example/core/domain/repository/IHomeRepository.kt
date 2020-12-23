package com.example.core.domain.repository

import androidx.paging.PagingData
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDeveloperModel
import com.example.core.domain.model.GameList
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    fun getDeveloper() : Flowable<Resource<List<GameDeveloperModel>>>

    fun getFavorite(): Flowable<List<Game>>

    fun setFavorite(game: Game, state: Boolean): Completable

    fun getGameById(id: Int): Flowable<Resource<List<Game>>>

    fun getGameList(): Flowable<Resource<List<GameList>>>

}