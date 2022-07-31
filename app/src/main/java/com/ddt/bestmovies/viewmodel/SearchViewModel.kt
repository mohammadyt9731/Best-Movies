package com.ddt.bestmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddt.bestmovies.models.home.ResponseMoviesList
import com.ddt.bestmovies.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    val searchMoviesList = MutableLiveData<ResponseMoviesList>()
    val loading = MutableLiveData<Boolean>()
    val isEmptyList = MutableLiveData<Boolean>()

    fun loadSearchMovies(name: String) = viewModelScope.launch {
        loading.postValue(true)
        val response = searchRepository.searchMoviesList(name)
        if (response.isSuccessful) {
            searchMoviesList.postValue(response.body())
            if (response.body()?.data!!.isNotEmpty()) {
                isEmptyList.postValue(false)
            } else {
                isEmptyList.postValue(true)
            }
        }
        loading.postValue(false)

    }


}