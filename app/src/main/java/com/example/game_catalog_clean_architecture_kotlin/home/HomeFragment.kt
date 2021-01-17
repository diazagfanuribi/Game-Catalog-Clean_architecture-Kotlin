package com.example.game_catalog_clean_architecture_kotlin.home


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.ui.DeveloperAdapter
import com.example.core.ui.GameAdapter
import com.example.game_catalog_clean_architecture_kotlin.R
import com.example.game_catalog_clean_architecture_kotlin.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var developerAdapter: DeveloperAdapter
    private lateinit var gameAdapter: GameAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        developerAdapter = DeveloperAdapter()
        gameAdapter = GameAdapter()
        gameAdapter.onItemClick = { game ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(game)
            findNavController().navigate(action)
        }


        developerAdapter.onItemClick = { selectedData ->
            val snackBar = Snackbar.make(
                view, "Coming soon",
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
            rvDeveloper.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvDeveloper.setHasFixedSize(true)
            rvDeveloper.adapter = developerAdapter
        }

        with(binding) {
            rvGame.layoutManager = LinearLayoutManager(context)
            rvGame.setHasFixedSize(false)
            rvGame.adapter = gameAdapter
        }
        homeViewModel.gameList.observe(viewLifecycleOwner) { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> binding.progressBarGame.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarGame.visibility = View.GONE
                        gameAdapter.setData(game.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarGame.visibility = View.GONE
                        binding.gameCautionLayout.visibility = View.VISIBLE
                        binding.gameCautionText.text = game.message
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        developerAdapter.onItemClick = null
        binding.nestedScrollHome.removeAllViewsInLayout()
        _binding = null
    }

}