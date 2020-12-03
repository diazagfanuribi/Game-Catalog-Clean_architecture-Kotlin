package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemGameListBinding
import com.example.core.domain.model.GameList



class GameAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<GameList, GameAdapter.GameViewHolder>(
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
        private val COMPARATOR = object : DiffUtil.ItemCallback<GameList>() {
            override fun areItemsTheSame(oldItem: GameList, newItem: GameList): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GameList, newItem: GameList): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class GameViewHolder(private val binding: ItemGameListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: GameList) {
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

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(game: GameList)
    }
}