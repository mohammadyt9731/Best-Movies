package com.ddt.bestmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddt.bestmovies.db.MovieEntity
import com.ddt.bestmovies.repository.favoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: favoriteRepository) :
    ViewModel() {

    val favoriteList = MutableLiveData<MutableList<MovieEntity>>()
    val isEmptyList = MutableLiveData<Boolean>()

    fun loadFavoriteList() = viewModelScope.launch {

        val list = favoriteRepository.favoriteList()
        if (list.isNotEmpty()) {
            favoriteList.postValue(list)
            isEmptyList.postValue(false)
        } else {
            isEmptyList.postValue(true)
        }
    }
}