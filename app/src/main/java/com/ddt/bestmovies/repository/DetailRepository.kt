package com.ddt.bestmovies.repository

import com.ddt.bestmovies.api.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api:ApiServices){

    suspend fun movieDetail(id:Int)=api.movieDetail(id)
}