package com.ddt.bestmovies.api

import com.ddt.bestmovies.models.register.BodyRegister
import com.ddt.bestmovies.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("register")
    suspend fun registerUser(@Body body:BodyRegister):Response<ResponseRegister>
}