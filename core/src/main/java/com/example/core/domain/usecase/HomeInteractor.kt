package com.example.core.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.example.core.data.HomeRepository
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDeveloperModel
import com.example.core.domain.model.GameList
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeInteractor @Inject constructor(private val repository: HomeRepository) : HomeUseCase {

    override fun getDeveloper(): Flowable<Resource<List<GameDeveloperModel>>> {
        return repository.getDeveloper()
    }

    override fun getFavorite(): Flowable<List<Game>> {
        return repository.getFavorite()
    }

    override fun setFavorite(game: Game, state: Boolean) =
        repository.setFavorite(game,state)


    override fun getGameById(id: Int): Flowable<Resource<Game>> {
        return repository.getGameById(id)
    }

    override fun getGameList(): Flowable<Resource<List<GameList>>> {
        return  repository.getGameList()
    }
}