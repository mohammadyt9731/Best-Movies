package com.ddt.bestmovies.repository

import com.ddt.bestmovies.api.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api:ApiServices) {

    suspend fun topMoviesList(id:Int)=api.topMoviesList(id)
}