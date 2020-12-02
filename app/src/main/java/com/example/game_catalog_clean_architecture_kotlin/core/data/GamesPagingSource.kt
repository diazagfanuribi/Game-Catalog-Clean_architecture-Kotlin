package com.example.game_catalog_clean_architecture_kotlin.core.data

import android.util.Log
import androidx.paging.rxjava2.RxPagingSource
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.RemoteDataSource
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.GameResponse
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.ListGameResponses
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.GameList
import com.example.game_catalog_clean_architecture_kotlin.core.utils.Mapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

const val STARTING_PAGE=1

class GamesPagingSource(val remoteDataSource: RemoteDataSource) : RxPagingSource<Int,GameList>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, GameList>> {
        val position = params.key?: STARTING_PAGE
        Log.d("RAWG", "MEMROSES DATA")

        return remoteDataSource.getGames(position, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map {
                Log.d("RAWG", it.toString())
                Mapper.mapResponsesToDomainsGameList(it.results) }
            .map {
                Log.d("RAWG", it.toString())
                toLoadResult(it,position)
            }.onErrorReturn {
                Log.d("RAWG", it.toString())
                LoadResult.Error(it) }

    }
    private fun toLoadResult(data: List<GameList>, position: Int): LoadResult<Int, GameList> {
        Log.d("RAWG", data.toString())
        return LoadResult.Page(
            data = data,
            prevKey = if (position == STARTING_PAGE) null else position - 1,
            nextKey = if (data.isEmpty()) null else position + 1
        )
    }


}