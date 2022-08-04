package com.ddt.bestmovies.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ddt.bestmovies.databinding.ItemHomeTopMoviesBinding
import com.ddt.bestmovies.models.home.ResponseMoviesList
import com.ddt.bestmovies.utils.Constants
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor() : RecyclerView.Adapter<TopMoviesAdapter.ViewHolder>() {

    //binding
    private lateinit var binding: ItemHomeTopMoviesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemHomeTopMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(true)
    }

    override fun getItemCount() = if(differ.currentList.size>5) 5 else differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(data: ResponseMoviesList.Data) {
            binding.apply {

                ivTopMovies.load(data.poster) {
                    crossfade(true)
                    crossfade(Constants.LOAD_IMAGE_CROSS_FADE)
                }
                tvTopMovieName.text = data.title
                tvTopMovieInfo.text = "${data.imdbRating} | ${data.country} | ${data.year}"

            }

        }

    }


    private val differCallBack = object : DiffUtil.ItemCallback<ResponseMoviesList.Data>() {
        override fun areItemsTheSame(
            oldItem: ResponseMoviesList.Data,
            newItem: ResponseMoviesList.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseMoviesList.Data,
            newItem: ResponseMoviesList.Data
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
}