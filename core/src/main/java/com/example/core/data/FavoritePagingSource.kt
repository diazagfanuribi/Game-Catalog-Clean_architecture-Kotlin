package com.example.core.data
//
//import android.util.Log
//import androidx.paging.rxjava2.RxPagingSource
//import com.example.core.data.source.local.LocalDataSource
//import com.example.core.domain.model.Game
//import com.example.core.utils.Mapper
//import io.reactivex.Single
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.schedulers.Schedulers
//const val FAV_STARTING_PAGE=0
//
//class FavoritePagingSource(val localDataSource: LocalDataSource) : RxPagingSource<Int, Game>() {
//    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Game>> {
//        val position = params.key?: FAV_STARTING_PAGE
//        Log.i("TOTAL", "FETCHING on page $position , size ${params.loadSize}")
//        return localDataSource.countFavorite()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .flatMap { value ->
//                localDataSource.getFavorite(position, params.loadSize)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .map{
//                        Mapper.mapGameEntitiesToDomain(it)
//                    }.map {
//                        Log.i("TOTAL", it.size.toString())
//                        Log.i("TOTAL", value.toString())
//                        toLoadResult(it,position,value)
//                    }.onErrorReturn {
//                        LoadResult.Error(it) }
//            }
//
//
//    }
//    private fun toLoadResult(data: List<Game>, position: Int, count: Int): LoadResult<Int, Game> {
//        return LoadResult.Page(
//            data = data,
//            prevKey = if (position == FAV_STARTING_PAGE) null else position - 1,
//            nextKey = if (position == count) null else position + 1
//        )
//    }
//
//
//}