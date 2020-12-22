package com.example.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favorite.di.DaggerFragmentComponent
import com.example.core.di.CoreDependency
import com.example.core.ui.FavoriteAdapter
import com.example.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    @Inject
    lateinit var factory: FavoriteViewModelFactory

    val favoriteViewModel: FavoriteViewModel by viewModels(){
        factory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreInjection()
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteAdapter = FavoriteAdapter()

        with(binding) {
            rvFavorite.layoutManager = LinearLayoutManager(applicationContext)
            rvFavorite.setHasFixedSize(true)
            rvFavorite.adapter = favoriteAdapter
        }
        favoriteViewModel.favoriteGame.observe(this, Observer{ dataTourism ->
            favoriteAdapter.setData(dataTourism)
        })


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