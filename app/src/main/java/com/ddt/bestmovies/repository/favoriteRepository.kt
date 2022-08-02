package com.ddt.bestmovies.repository

import com.ddt.bestmovies.db.MovieDao
import javax.inject.Inject

class favoriteRepository @Inject constructor(private val dao:MovieDao) {

    fun favoriteList()=dao.getAllMovies()
}