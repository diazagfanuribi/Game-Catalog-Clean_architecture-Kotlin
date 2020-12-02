package com.example.game_catalog_clean_architecture_kotlin

import android.app.Application
import com.dicoding.game_catalog_clean_architecture_kotlin.di.AppComponent
import com.example.game_catalog_clean_architecture_kotlin.core.di.CoreComponent
import com.example.game_catalog_clean_architecture_kotlin.core.di.DaggerCoreComponent

open class MyApplication : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}