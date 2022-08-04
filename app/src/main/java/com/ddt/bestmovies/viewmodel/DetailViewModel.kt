package com.ddt.bestmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddt.bestmovies.db.MovieEntity
import com.ddt.bestmovies.models.detail.ResponseMovieDetail
import com.ddt.bestmovies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository) :
    ViewModel() {

    //api
    val movieDetail = MutableLiveData<ResponseMovieDetail>()
    val loading = MutableLiveData<Boolean>()

    fun loadMovieDetail(id: Int) = viewModelScope.launch {
        loading.postValue(true)
        val response = detailRepository.movieDetail(id)
        if (response.isSuccessful) {
            movieDetail.postValue(response.body())
        }
        loading.postValue(false)
    }

    //DateBase
    val isFavorite = MutableLiveData<Boolean>()

    suspend fun existMovie(id: Int) = withContext(viewModelScope.coroutineContext) {
        detailRepository.existMovie(id)
    }

    fun favoriteMovie(id: Int, movieEntity: MovieEntity) = viewModelScope.launch {
        val exist = detailRepository.existMovie(id)

        if (exist) {
            isFavorite.postValue(false)
            detailRepository.deleteMovie(movieEntity)

        } else {
            isFavorite.postValue(true)
            detailRepository.insertMovie(movieEntity)
        }

    }


}