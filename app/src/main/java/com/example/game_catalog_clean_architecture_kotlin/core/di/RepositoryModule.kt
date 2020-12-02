package com.example.game_catalog_clean_architecture_kotlin.core.di
import com.example.game_catalog_clean_architecture_kotlin.core.data.HomeRepository
import com.example.game_catalog_clean_architecture_kotlin.core.domain.repository.IHomeRepository
import dagger.Binds
import dagger.Module


@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(homeRepository: HomeRepository): IHomeRepository

}