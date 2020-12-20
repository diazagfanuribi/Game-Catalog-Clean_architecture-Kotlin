package com.example.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.ItemGameListBinding
import com.example.core.domain.model.Game


class FavoriteAdapter() :
    PagingDataAdapter<Game, FavoriteAdapter.GameViewHolder>(
        COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.pk == newItem.pk
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class GameViewHolder(private val binding: ItemGameListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Game) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image_url)
                    .into(imageGame)
                textviewTitle.text = data.name
                textviewRelease.text = data.release
                textviewRating.text = data.rating.toString()
                ratingBarGame.rating = data.rating

            }
        }
    }
}