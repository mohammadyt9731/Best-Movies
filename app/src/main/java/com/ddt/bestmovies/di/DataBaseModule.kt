package com.ddt.bestmovies.di

import android.content.Context
import androidx.room.Room
import com.ddt.bestmovies.db.MovieDataBase
import com.ddt.bestmovies.db.MovieEntity
import com.ddt.bestmovies.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context)=Room.databaseBuilder(
        context,MovieDataBase::class.java,Constants.FAVORITE_DATA_BASE
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun provideDao(dp:MovieDataBase)=dp.movieDao()

    @Provides
    @Singleton
    fun provideEntity()=MovieEntity()
}

