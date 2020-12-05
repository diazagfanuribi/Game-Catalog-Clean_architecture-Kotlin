package com.example.game_catalog_clean_architecture_kotlin.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.game_catalog_clean_architecture_kotlin.MyApplication
import com.example.game_catalog_clean_architecture_kotlin.R
import com.example.core.data.Resource
import com.example.core.domain.model.GameList
import com.example.core.ui.DeveloperAdapter
import com.example.core.ui.GameAdapter
import com.example.game_catalog_clean_architecture_kotlin.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), GameAdapter.OnItemClickListener {
    private val mDisposable = CompositeDisposable()

//    @Inject
//    lateinit var factory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels()
//    {
//        factory
//    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var developerAdapter: DeveloperAdapter
    private lateinit var gameAdapter: GameAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        developerAdapter = DeveloperAdapter()
        gameAdapter = GameAdapter(this)

        developerAdapter.onItemClick = { selectedData ->
            val snackBar = Snackbar.make(
                view, "Replace with your own action",
                Snackbar.LENGTH_LONG
            ).setAction("Action", null)
            snackBar.setActionTextColor(Color.BLUE)
            snackBar.show()
        }
        homeViewModel.developer.observe(viewLifecycleOwner) { developer ->
            if (developer != null) {
                when (developer) {
                    is Resource.Loading -> binding.progressBarDev.visibility = View.VISIBLE
                    is Resource.Success -> {
                        Log.d("RRAWG", developer.data.toString())
                        binding.progressBarDev.visibility = View.GONE
                        developerAdapter.setData(developer.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarDev.visibility = View.GONE
                        binding.devCautionLayout.visibility = View.VISIBLE
                        binding.devCautionText.text = developer.message
                    }
                }
            }
        }

        with(binding) {
            rvDeveloper.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvDeveloper.setHasFixedSize(true)
            rvDeveloper.adapter = developerAdapter
            dev_caution_button.setOnClickListener {

            }
        }

        with(binding) {
            rvGame.layoutManager = LinearLayoutManager(context)
            rvGame.setHasFixedSize(true)
            rvGame.adapter = gameAdapter
//            adapter = gameAdapter.withLoadStateFooter(
//                footer =
            game_caution_button.setOnClickListener {
                gameAdapter.retry()
            }

        }

        gameAdapter.addLoadStateListener { loadState ->

            binding.apply {
                progressBarGame.isVisible = loadState.source.refresh is LoadState.Loading
                rvGame.isVisible = loadState.source.refresh is LoadState.NotLoading
                gameCautionLayout.isVisible = loadState.source.refresh is LoadState.Error
            }
        }

        mDisposable.add(homeViewModel.getGames().subscribe {
            gameAdapter.submitData(lifecycle, it)
        })

    }



    override fun onDestroyView() {
        mDisposable.dispose()
        super.onDestroyView()
    }

    override fun onItemClick(game: GameList) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(game)
        findNavController().navigate(action)
    }

}