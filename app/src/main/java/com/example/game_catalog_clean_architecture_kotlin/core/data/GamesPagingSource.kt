package com.example.game_catalog_clean_architecture_kotlin.core.data

import androidx.paging.rxjava2.RxPagingSource
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.RemoteDataSource
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.GameResponse
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.ListGameResponses
import com.example.game_catalog_clean_architecture_kotlin.core.utils.Mapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

const val STARTING_PAGE=0
class GamesPagingSource(val remoteDataSource: RemoteDataSource) : RxPagingSource<Int,GameResponse>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, GameResponse>> {
        val position = params.key?: STARTING_PAGE

        return remoteDataSource.getGames(position, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(it,position)
            }.onErrorReturn {
                LoadResult.Error(it) }

    }
    private fun toLoadResult(data: ListGameResponses, position: Int): LoadResult<Int, GameResponse> {
        return LoadResult.Page(
            data = data.results,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.count) null else position + 1
        )
    }


}