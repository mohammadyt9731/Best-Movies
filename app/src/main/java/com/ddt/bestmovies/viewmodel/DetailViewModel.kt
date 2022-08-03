package com.ddt.bestmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddt.bestmovies.models.detail.ResponseMovieDetail
import com.ddt.bestmovies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository) :
    ViewModel() {

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


}