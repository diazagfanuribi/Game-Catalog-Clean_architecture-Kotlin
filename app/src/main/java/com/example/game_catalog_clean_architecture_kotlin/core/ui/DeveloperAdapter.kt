package com.example.game_catalog_clean_architecture_kotlin.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.game_catalog_clean_architecture_kotlin.R
import com.example.game_catalog_clean_architecture_kotlin.core.domain.model.GameDeveloperModel
import com.example.game_catalog_clean_architecture_kotlin.databinding.ItemDeveloperCardBinding
import java.util.ArrayList

class DeveloperAdapter : RecyclerView.Adapter<DeveloperAdapter.ListViewHolder>() {

    private var listData = ArrayList<GameDeveloperModel>()
    var onItemClick: ((GameDeveloperModel) -> Unit)? = null

    fun setData(newListData: List<GameDeveloperModel>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_developer_card, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemDeveloperCardBinding.bind(itemView)
        fun bind(data: GameDeveloperModel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image_url)
                    .into(imageDeveloper)
                textviewDeveloper.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}