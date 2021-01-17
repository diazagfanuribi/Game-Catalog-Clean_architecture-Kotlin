package com.example.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.CoreDependency
import com.example.core.ui.FavoriteAdapter
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.favorite.detail.DetailFavoritActivity
import com.example.favorite.di.DaggerFragmentComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter

    @Inject
    lateinit var factory: FavoriteViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreInjection()
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteAdapter = FavoriteAdapter()

        favoriteAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailFavoritActivity::class.java)
            intent.putExtra(DetailFavoritActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        with(binding) {
            rvFavorite.layoutManager = LinearLayoutManager(applicationContext)
            rvFavorite.setHasFixedSize(true)
            rvFavorite.adapter = favoriteAdapter
        }
        favoriteViewModel.favoriteGame.observe(this, Observer { dataTourism ->
            favoriteAdapter.setData(dataTourism)
            if (dataTourism.isEmpty()) {
                binding.emptyText.visibility = View.VISIBLE
            } else {
                binding.emptyText.visibility = View.GONE
            }
        })


        setSupportActionBar(binding.toolbar)
    }

    private fun initCoreInjection() {
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