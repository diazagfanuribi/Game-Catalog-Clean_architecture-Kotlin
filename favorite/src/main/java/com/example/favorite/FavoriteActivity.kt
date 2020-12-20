package com.example.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.favorite.di.DaggerFragmentComponent
import com.example.core.di.CoreDependency
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.game_catalog_clean_architecture_kotlin.databinding.ActivityMainBinding
import dagger.hilt.android.EntryPointAccessors

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreInjection()
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
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