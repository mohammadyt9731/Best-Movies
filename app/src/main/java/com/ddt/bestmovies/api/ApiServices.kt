package com.ddt.bestmovies.api

import com.ddt.bestmovies.models.home.ResponseGenresList
import com.ddt.bestmovies.models.home.ResponseMoviesList
import com.ddt.bestmovies.models.register.BodyRegister
import com.ddt.bestmovies.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {

    @POST("register")
    suspend fun registerUser(@Body body:BodyRegister):Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun topMoviesList(@Path("genre_id")id:Int):Response<ResponseMoviesList>

    @GET("genres")
    suspend fun genresList():Response<ResponseGenresList>

    @GET("movies")
    suspend fun lastMoviesList():Response<ResponseMoviesList>

}