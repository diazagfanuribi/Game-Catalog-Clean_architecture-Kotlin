package com.example.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.GameDeveloperResponse
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDeveloperModel
import com.example.core.domain.model.GameList
import com.example.core.domain.repository.IHomeRepository
import com.example.core.utils.Mapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
class HomeRepository @Inject constructor(
     val remoteDataSource: RemoteDataSource,
     val localDataSource: LocalDataSource
) : IHomeRepository {
    override fun getGames(): Flowable<PagingData<GameList>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance =30,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GamesPagingSource(remoteDataSource) }
        ).flowable
    }

    override fun getDeveloper(): Flowable<Resource<List<GameDeveloperModel>>> =
        object : NetworkBoundResource<List<GameDeveloperModel>, List<GameDeveloperResponse>>() {
            override fun loadFromDB(): Flowable<List<GameDeveloperModel>> {
                return localDataSource.getAllDeveloper()
                    .map {
                        Mapper.mapDeveloperEntitiesToDomains(it) }
            }

            override fun shouldFetch(data: List<GameDeveloperModel>?): Boolean = true

            override fun createCall(): Flowable<ApiResponse<List<GameDeveloperResponse>>> {
                return remoteDataSource.getDeveloper()
            }

            override fun saveCallResult(data: List<GameDeveloperResponse>) {
                val gameDeveloper = Mapper.mapDeveloperResponseToEntities(data)
                localDataSource.addDeveloper(gameDeveloper)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getFavorite(): Flowable<List<Game>> {
        return localDataSource.getFavorite().map{
            Mapper.mapGameEntitiesToDomain(it)
        }
    }

    override fun setFavorite(game: Game, state: Boolean) {
        val gameEntity = Mapper.mapDomainToEntity(game)
        gameEntity.isFavorite = state
        localDataSource.updateFavorite(gameEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}