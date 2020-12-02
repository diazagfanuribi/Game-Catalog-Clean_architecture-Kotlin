package com.example.game_catalog_clean_architecture_kotlin.core.di

import android.content.Context
import com.example.game_catalog_clean_architecture_kotlin.core.domain.repository.IHomeRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository() : IHomeRepository
}