package com.ddt.bestmovies.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStore @Inject constructor(@ApplicationContext val context: Context) {

    companion object{
        private val Context.dataSore:DataStore<Preferences> by preferencesDataStore(Constants.USER_INFO_DATA_STORE)
        private val USER_TOKEN_KEY= stringPreferencesKey(Constants.USER_TOKEN_KEY)
    }

    suspend fun saveUserToken(token:String){
        context.dataSore.edit {
            it[USER_TOKEN_KEY]=token
        }
    }

    fun getUserToken()= context.dataSore.data.map {
        it[USER_TOKEN_KEY]?:""
    }


}