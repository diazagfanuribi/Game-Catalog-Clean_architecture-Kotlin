package com.dicoding.game_catalog_clean_architecture_kotlin.di

import com.example.game_catalog_clean_architecture_kotlin.core.di.CoreComponent
import com.example.game_catalog_clean_architecture_kotlin.home.HomeFragment
import dagger.Component


@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)

}