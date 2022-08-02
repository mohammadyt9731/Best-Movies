package com.ddt.bestmovies.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDataBase:RoomDatabase() {

abstract fun movieDao():MovieDao
}