package com.ddt.bestmovies.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ddt.bestmovies.databinding.ItemHomeGenreBinding
import com.ddt.bestmovies.databinding.ItemHomeTopMoviesBinding
import com.ddt.bestmovies.models.home.ResponseGenresList
import com.ddt.bestmovies.models.home.ResponseGenresList.*
import com.ddt.bestmovies.models.home.ResponseMoviesList
import com.ddt.bestmovies.utils.Constants
import javax.inject.Inject

class GenresListAdapter @Inject constructor() : RecyclerView.Adapter<GenresListAdapter.ViewHolder>() {

    //binding
    private lateinit var binding: ItemHomeGenreBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemHomeGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(true)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(data: ResponseGenreListItem) {
            binding.apply {

               tvGenreName.text=data.name

            }

        }

    }


    private val differCallBack = object : DiffUtil.ItemCallback<ResponseGenreListItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseGenreListItem,
            newItem: ResponseGenreListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseGenreListItem,
            newItem: ResponseGenreListItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
}