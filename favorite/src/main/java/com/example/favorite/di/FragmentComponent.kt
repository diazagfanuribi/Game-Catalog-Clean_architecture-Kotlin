package com.example.favorite.di

import android.app.Activity
import com.example.favorite.FavoriteActivity
import com.example.core.di.CoreDependency
import com.example.favorite.detail.DetailFavoritActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [CoreDependency::class],
    modules = [FavoriteModule::class]
)
interface FragmentComponent {
    fun inject(favoriteActivity: FavoriteActivity)

    fun inject(detailFavoritActivity: DetailFavoritActivity)


    @Component.Factory
    interface Factory {
        fun create(
            dependentModule: CoreDependency,
            @BindsInstance activity:Activity
        ): FragmentComponent
    }

}