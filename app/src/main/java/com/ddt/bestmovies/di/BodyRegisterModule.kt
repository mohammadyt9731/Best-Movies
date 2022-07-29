package com.ddt.bestmovies.di

import com.ddt.bestmovies.models.register.BodyRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BodyRegisterModule {

    @Provides
    @Singleton
    fun provideBodyRegister()=BodyRegister()
}