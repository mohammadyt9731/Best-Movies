package com.ddt.bestmovies.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ddt.bestmovies.databinding.ItemHomeLastMoviesBinding
import com.ddt.bestmovies.databinding.ItemHomeTopMoviesBinding
import com.ddt.bestmovies.models.home.ResponseMoviesList
import com.ddt.bestmovies.utils.Constants
import javax.inject.Inject

class LastMoviesAdapter @Inject constructor() : RecyclerView.Adapter<LastMoviesAdapter.ViewHolder>() {

    //binding
    private lateinit var binding: ItemHomeLastMoviesBinding

    //other
    private var moviesList= emptyList<ResponseMoviesList.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemHomeLastMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(moviesList[position])
        holder.setIsRecyclable(true)
    }

    override fun getItemCount() = moviesList.size

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(data: ResponseMoviesList.Data) {
            binding.apply {

                tvName.text=data.title
                tvRate.text=data.imdbRating
                tvCountry.text=data.country
                tvYear.text=data.year

                ivPosterImage.load(data.poster){
                    crossfade(true)
                    crossfade(Constants.LOAD_IMAGE_CROSS_FADE)
                }

            }

        }

    }


    fun setNewListDate(newList:List<ResponseMoviesList.Data>){

        val lastMoviesDiffUtils=LastMoviesDiffUtils(moviesList,newList)
        val diffUtils=DiffUtil.calculateDiff(lastMoviesDiffUtils)
        moviesList=newList
        diffUtils.dispatchUpdatesTo(this)
    }

    class LastMoviesDiffUtils( private val oldList:List<ResponseMoviesList.Data>
    , private val newList:List<ResponseMoviesList.Data>):DiffUtil.Callback(){


        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

    }

}