package com.example.core.data

const val STARTING_PAGE = 1

//class GamesPagingSource(val remoteDataSource: RemoteDataSource) : RxPagingSource<Int, GameList>() {
//    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, GameList>> {
//        val position = params.key?: STARTING_PAGE
//        Log.d("RAWG", "MEMROSES DATA")
//
//        return remoteDataSource.getGames(position, params.loadSize)
//            .subscribeOn(Schedulers.io())
//            .map {
//                Mapper.mapResponsesToDomainsGameList(it.results) }
//            .map {
//                toLoadResult(it,position)
//            }.onErrorReturn {
//                LoadResult.Error(it) }
//
//    }
//    private fun toLoadResult(data: List<GameList>, position: Int): LoadResult<Int, GameList> {
//        return LoadResult.Page(
//            data = data,
//            prevKey = if (position == STARTING_PAGE) null else position - 1,
//            nextKey = if (data.isEmpty()) null else position + 1
//        )
//    }
//
//
//}