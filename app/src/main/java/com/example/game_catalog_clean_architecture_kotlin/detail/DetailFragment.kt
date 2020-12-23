package com.example.game_catalog_clean_architecture_kotlin.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.core.domain.model.GameList
import com.example.game_catalog_clean_architecture_kotlin.R
import com.example.game_catalog_clean_architecture_kotlin.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object{
        const val BUNDLE_ARGS = "Game"
    }

    override fun setArguments(args: Bundle?) {
        if (args != null) {
            super.setArguments(Bundle(args).apply {
                putBundle(BUNDLE_ARGS, args) // Wrap the arguments as BUNDLE_ARGS
            })
        } else {
            super.setArguments(null)
        }
    }

    private val args by navArgs<DetailFragmentArgs>()

    lateinit private var binding: FragmentDetailBinding

    private val viewModel : DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        viewModel.gameDetail.observe(viewLifecycleOwner){ detail ->
            if (detail != null) {
                when (detail) {
                    is Resource.Loading ->{
                        binding.gameCautionLayout.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE}

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if (!detail.data.isNullOrEmpty()){
                            binding.gameCautionLayout.visibility = View.GONE
                            detail.data?.let {
                                Log.i("Fetch to ui",it.toString())
                                initUI(it.first()) }
                        }else{
                            binding.gameCautionLayout.visibility = View.VISIBLE
                            binding.gameCautionText.text = "Empty detail. Please Retry"
                            binding.gameCautionButton.setOnClickListener {
                                viewModel.getGameDetail()
                            }

                        }

                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.gameCautionLayout.visibility = View.VISIBLE
                        binding.gameCautionText.text = detail.message
                        binding.gameCautionButton.setOnClickListener {
                            viewModel.getGameDetail()
                        }
                    }
                }
            }
        }


    }


    fun initUIArgs(data : GameList){
        with(binding){
            Glide.with(this@DetailFragment)
                .load(data.image_url)
                .into(imageView)
            textViewTitle.text = data.name
            textviewRating.text = data.rating.toString()
            ratingBarGame.rating = data.rating
            setStatusFavorite(data.isFavorite)
        }
    }
    fun initUI(data : Game){
        with(binding){
            Glide.with(this@DetailFragment)
                .load(data.image_url)
                .into(imageView)
            textViewTitle.text = data.name
            textViewDescription.text = data.description
            textViewCreator.text = data.website
            textviewRating.text = data.rating.toString()
            ratingBarGame.rating = data.rating
            setStatusFavorite(data.isFavorite)
            fab.setOnClickListener {
                viewModel.updateFavorite(data,!data.isFavorite)
                setStatusFavorite(!data.isFavorite)

            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_star_fill_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_star_border_white))
        }
    }
}