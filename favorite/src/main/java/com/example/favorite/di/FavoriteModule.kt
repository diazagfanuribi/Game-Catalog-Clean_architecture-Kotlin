package com.example.favorite.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core.ui.ViewModelFactory
import com.example.favorite.FavoriteViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(ActivityComponent::class)
class FavoriteModule {
    @Provides
    fun provideFavoriteViewModel(fragment: Fragment, factory: ViewModelFactory) =
        ViewModelProvider(fragment, factory).get(FavoriteViewModel::class.java)
}