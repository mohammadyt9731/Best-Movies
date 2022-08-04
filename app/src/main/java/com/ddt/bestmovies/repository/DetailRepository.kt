package com.ddt.bestmovies.repository

import com.ddt.bestmovies.api.ApiServices
import com.ddt.bestmovies.db.MovieDao
import com.ddt.bestmovies.db.MovieEntity
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api:ApiServices,private val dao:MovieDao){
    //api
    suspend fun movieDetail(id:Int)=api.movieDetail(id)

    //DateBase
    suspend fun insertMovie(movieEntity: MovieEntity)=dao.insertMovie(movieEntity)
    suspend fun deleteMovie(movieEntity: MovieEntity)=dao.deleteMovie(movieEntity)
    suspend fun existMovie(id: Int)=dao.existMovie(id)

}