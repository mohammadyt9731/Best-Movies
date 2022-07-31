package com.ddt.bestmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddt.bestmovies.models.home.ResponseGenresList
import com.ddt.bestmovies.models.home.ResponseMoviesList
import com.ddt.bestmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    val topMoviesList = MutableLiveData<ResponseMoviesList>()
    val genresList = MutableLiveData<ResponseGenresList>()
    val lastMoviesList=MutableLiveData<ResponseMoviesList>()

    val loading=MutableLiveData<Boolean>()

    fun loadTopMoviesList(id: Int) {
        viewModelScope.launch {
            val response = homeRepository.topMoviesList(id)
            if (response.isSuccessful) {
                topMoviesList.postValue(response.body())
            }
        }
    }

    fun loadGenresList() {
        viewModelScope.launch {
            val response = homeRepository.genresList()
            if (response.isSuccessful) {
                genresList.postValue(response.body())
            }
        }
    }

    fun loadLastMoviesList(){
        loading.postValue(true)
        viewModelScope.launch {
            val response=homeRepository.lastMoviesList()
            if(response.isSuccessful){
                lastMoviesList.postValue(response.body())
            }
            loading.postValue(false)
        }


    }

}