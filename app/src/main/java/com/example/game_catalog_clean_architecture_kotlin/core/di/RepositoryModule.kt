package com.example.game_catalog_clean_architecture_kotlin.core.di
import com.example.game_catalog_clean_architecture_kotlin.core.data.HomeRepository
import com.example.game_catalog_clean_architecture_kotlin.core.domain.repository.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(homeRepository: HomeRepository): IHomeRepository

}