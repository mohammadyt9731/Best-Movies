package com.ddt.bestmovies.db

import androidx.room.*
import com.ddt.bestmovies.utils.Constants

@Dao
interface MovieDao {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertMovie(entity: MovieEntity)

    @Delete
    suspend fun deleteMovie(entity: MovieEntity)

    @Query("SELECT * FROM ${Constants.FAVORITE_TABLE}")
    fun getAllMovies():MutableList<MovieEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM ${Constants.FAVORITE_TABLE} WHERE id=:movieId)")
    suspend fun existMovie(movieId:Int):Boolean
}