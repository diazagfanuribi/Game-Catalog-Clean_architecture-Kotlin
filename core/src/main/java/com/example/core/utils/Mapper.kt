package com.example.core.utils

import com.example.core.data.source.local.entity.GameDeveloperEntity
import com.example.core.data.source.local.entity.GameEntity
import com.example.core.data.source.remote.response.GameDetailResponse
import com.example.core.data.source.remote.response.GameDeveloperResponse
import com.example.core.data.source.remote.response.GameResponse
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameDeveloperModel
import com.example.core.domain.model.GameList

object Mapper {
    fun mapGameResponsesToEntities(input: List<GameDetailResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                    pk = it.id,
                    name=it.name ?: "N/A",
                    release = it.release ?: "N/A",
                    rating = it.rating  ?: 0F,
                    image_url = it.image_url  ?: "N/A",
                    description = it.description  ?: "N/A",
                    image_url_additional = it.image_url_additional  ?: "N/A",
                    website = it.website  ?: "N/A",
                    dominant_color = it.dominant_color  ?: "N/A",
                    genre = it.genres  ?: "N/A",
                    isFavorite = it.isFavorite
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapGameResponseToEntity(it: GameDetailResponse): GameEntity {
            val game = GameEntity(
                pk = it.id,
                name=it.name ?: "N/A",
                release = it.release ?: "N/A",
                rating = it.rating  ?: 0F,
                image_url = it.image_url  ?: "N/A",
                description = it.description  ?: "N/A",
                image_url_additional = it.image_url_additional  ?: "N/A",
                website = it.website  ?: "N/A",
                dominant_color = it.dominant_color  ?: "N/A",
                genre = it.genres  ?: "N/A",
                isFavorite = it.isFavorite
            )
        return game

    }

    fun mapGameEntitiesToDomain(input: List<GameEntity>): List<Game> =
            input.map {
                Game(
                        pk = it.pk,
                        name=it.name,
                        release = it.release,
                        rating = it.rating,
                        image_url = it.image_url,
                        description = it.description,
                        image_url_additional = it.image_url_additional,
                        website = it.website,
                        dominant_color = it.dominant_color,
                        genre = it.genre,
                        isFavorite = it.isFavorite
                )
            }

    fun mapGameEntityToDomain(it: GameEntity): Game= Game(
                pk = it.pk,
                name=it.name,
                release = it.release,
                rating = it.rating,
                image_url = it.image_url,
                description = it.description,
                image_url_additional = it.image_url_additional,
                website = it.website,
                dominant_color = it.dominant_color,
                genre = it.genre,
                isFavorite = it.isFavorite
            )


    fun mapDomainToEntity(it: Game) = GameEntity(
            pk = it.pk,
            name=it.name,
            release = it.release,
            rating = it.rating,
            image_url = it.image_url,
            description = it.description,
            image_url_additional = it.image_url_additional,
            website = it.website,
            dominant_color = it.dominant_color,
            genre = it.genre,
            isFavorite = it.isFavorite
    )

    fun mapResponsesToDomainsGameList(input : List<GameResponse>): List<GameList>{
        val gameLists = ArrayList<GameList>()
        input.map {
            val gameList = GameList(
                id = it.id,
                name = it.name ?: "Unknown",
                release = it.release ?: "-",
                rating = it.rating ?: 0F,
                image_url = it.image_url ?: "",
                isFavorite = it.isFavorite
            )
            gameLists.add(gameList)
        }
        return gameLists
    }

    fun mapDeveloperEntitiesToDomains(input : List<GameDeveloperEntity>): List<GameDeveloperModel>{
        val gameDeveloper = ArrayList<GameDeveloperModel>()
        input.map {
            val gameList = GameDeveloperModel(
                id = it.id,
                name = it.name,
                image_url = it.image_url
            )
            gameDeveloper.add(gameList)
        }
        return gameDeveloper
    }

    fun mapDeveloperDomainsToEntities(input : List<GameDeveloperModel>): List<GameDeveloperEntity>{
        val gameDeveloper = ArrayList<GameDeveloperEntity>()
        input.map {
            val gameList = GameDeveloperEntity(
                id = it.id,
                name = it.name,
                image_url = it.image_url
            )
            gameDeveloper.add(gameList)
        }
        return gameDeveloper
    }

    fun mapDeveloperResponseToEntities(input : List<GameDeveloperResponse>): List<GameDeveloperEntity>{
        val gameDeveloper = ArrayList<GameDeveloperEntity>()
        input.map {
            val gameList = GameDeveloperEntity(
                id = it.id,
                name = it.name,
                image_url = it.image_url
            )
            gameDeveloper.add(gameList)
        }
        return gameDeveloper
    }
}