package com.example.game_catalog_clean_architecture_kotlin.core.di
import dagger.Binds
import dagger.Module


@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

//    @Binds
//    abstract fun provideRepository(tourismRepository: TourismRepository): ITourismRepository

}