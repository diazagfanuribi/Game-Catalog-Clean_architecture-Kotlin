package com.example.core.domain.usecase

import androidx.paging.PagingData
import com.example.core.data.HomeRepository
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDeveloperModel
import com.example.core.domain.model.GameList
import io.reactivex.Flowable
import javax.inject.Inject

class HomeInteractor @Inject constructor(private val repository: HomeRepository) : HomeUseCase {
    override fun getGames(): Flowable<PagingData<GameList>> {
        return repository.getGames()
    }

    override fun getDeveloper(): Flowable<Resource<List<GameDeveloperModel>>> {
        return repository.getDeveloper()
    }

    override fun getFavorite(): Flowable<List<Game>> {
        return repository.getFavorite()
    }

    override fun setFavorite(game: Game, state: Boolean) {
        repository.setFavorite(game,state)
    }
}