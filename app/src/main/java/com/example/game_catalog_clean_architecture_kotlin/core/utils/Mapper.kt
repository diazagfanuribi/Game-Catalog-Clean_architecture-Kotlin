package com.example.game_catalog_clean_architecture_kotlin.core.utils

import com.example.game_catalog_clean_architecture_kotlin.core.data.source.local.entity.GameEntity
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.GameDetailResponse
import com.example.game_catalog_clean_architecture_kotlin.core.data.source.remote.response.GameResponse
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.Game

object Mapper {
    fun mapGameResponsesToEntities(input: List<GameDetailResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                    pk = it.id,
                    name=it.name,
                    release = it.release,
                    rating = it.rating,
                    image_url = it.image_url,
                    description = it.description,
                    image_url_additional = it.image_url_additional,
                    website = it.website,
                    dominant_color = it.dominant_color,
                    genre = it.genres,
                    isFavorite = it.isFavorite
            )
            gameList.add(game)
        }
        return gameList
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
}