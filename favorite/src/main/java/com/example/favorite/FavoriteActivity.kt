package com.example.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.favorite.di.DaggerFragmentComponent
import com.example.core.di.CoreDependency
import dagger.hilt.android.EntryPointAccessors

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreInjection()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }

    fun initCoreInjection(){
        val coreModuleDependencies = EntryPointAccessors.fromApplication(
            applicationContext,
            CoreDependency::class.java
        )
        DaggerFragmentComponent.factory().create(
            dependentModule = coreModuleDependencies,
            activity = this
        )
            .inject(this)
    }
}