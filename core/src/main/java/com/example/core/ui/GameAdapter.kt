package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemGameListBinding
import com.example.core.domain.model.GameList
import java.util.*


class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

    private var listData = ArrayList<GameList>()
    var onItemClick: ((GameList) -> Unit)? = null

    fun setData(newListData: List<GameList>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_list, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGameListBinding.bind(itemView)
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
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}