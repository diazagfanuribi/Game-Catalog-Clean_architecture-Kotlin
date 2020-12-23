package com.example.favorite.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core.ui.ViewModelFactory
import com.example.favorite.FavoriteViewModel
import com.example.favorite.detail.DetailFavoritViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class FavoriteModule {
    @Provides
    fun provideFavoriteViewModel(fragment: Fragment, factory: ViewModelFactory) =
        ViewModelProvider(fragment, factory).get(FavoriteViewModel::class.java)

    @Provides
    fun provideDetailFavoritViewModel(fragment: Fragment, factory: ViewModelFactory) =
        ViewModelProvider(fragment, factory).get(DetailFavoritViewModel::class.java)
}