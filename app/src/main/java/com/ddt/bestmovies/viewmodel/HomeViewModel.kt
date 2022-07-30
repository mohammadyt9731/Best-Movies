package com.ddt.bestmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddt.bestmovies.models.home.ResponseMoviesList
import com.ddt.bestmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    val topMoviesList=MutableLiveData<ResponseMoviesList>()

    fun loadTopMoviesList(id:Int){
        viewModelScope.launch {
            val response=homeRepository.topMoviesList(id)
            if(response.isSuccessful){
                topMoviesList.postValue(response.body())
            }
        }


    }


}