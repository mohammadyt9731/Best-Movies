package com.ddt.bestmovies.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ddt.bestmovies.databinding.ItemDetailImagesBinding
import javax.inject.Inject

class DetailImageAdapter @Inject constructor() : RecyclerView.Adapter<DetailImageAdapter.ViewHolder>() {

    private lateinit var binding: ItemDetailImagesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImageAdapter.ViewHolder {
        binding = ItemDetailImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: DetailImageAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: String) {
            binding.apply {
                ivMovieImage.load(item) {
                    crossfade(true)
                    crossfade(800)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}