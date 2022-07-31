package com.ddt.bestmovies.repository

import com.ddt.bestmovies.api.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api:ApiServices) {

    suspend fun searchMoviesList(name:String)=api.searchMoviesList(name)
}