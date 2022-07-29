package com.ddt.bestmovies.repository

import com.ddt.bestmovies.api.ApiServices
import com.ddt.bestmovies.models.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api:ApiServices){

    suspend fun registerUser(body:BodyRegister) = api.registerUser(body)
}