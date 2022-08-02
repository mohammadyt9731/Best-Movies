package com.ddt.bestmovies.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ddt.bestmovies.utils.Constants

@Entity(tableName = Constants.FAVORITE_TABLE)
data class MovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var poster: String = "",
    var title: String = "",
    var rate: String = "",
    var country: String = "",
    var year: String = "",
)