package com.dicoding.game_catalog_clean_architecture_kotlin.di


import com.example.game_catalog_clean_architecture_kotlin.core.domain.usecase.HomeInteractor
import com.example.game_catalog_clean_architecture_kotlin.core.domain.usecase.HomeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideHomeUseCase(homeInteractor: HomeInteractor): HomeUseCase

}
