package com.example.favorite.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.core.di.CoreDependency
import com.example.core.domain.model.Game
import com.example.favorite.DetailFavoritViewModelFactory
import com.example.favorite.databinding.ActivityDetailFavoritBinding
import com.example.favorite.di.DaggerFragmentComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DetailFavoritActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    @Inject
    lateinit var factory: DetailFavoritViewModelFactory

    private val viewModel: DetailFavoritViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityDetailFavoritBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreInjection()
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailFavorite = intent.getParcelableExtra<Game>(EXTRA_DATA)
        detailFavorite?.let { initUI(it) }
        setSupportActionBar(binding.toolbar)
    }

    private fun initUI(data: Game) {
        with(binding) {
            Glide.with(this@DetailFavoritActivity)
                .load(data.image_url)
                .into(imageView)
            textViewTitle.text = data.name
            textViewDescription.text = data.description
            textViewCreator.text = data.website
            textviewRating.text = data.rating.toString()
            ratingBarGame.rating = data.rating
            setStatusFavorite(data.isFavorite)
            fab.setOnClickListener {
                viewModel.updateFavorite(data, !data.isFavorite)
                setStatusFavorite(!data.isFavorite)
                data.isFavorite = !data.isFavorite
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    com.example.game_catalog_clean_architecture_kotlin.R.drawable.ic_star_fill_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    com.example.game_catalog_clean_architecture_kotlin.R.drawable.ic_star_border_white
                )
            )
        }
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