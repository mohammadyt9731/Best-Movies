package com.ddt.bestmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddt.bestmovies.models.register.BodyRegister
import com.ddt.bestmovies.models.register.ResponseRegister
import com.ddt.bestmovies.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository) : ViewModel() {

    val responseRegister=MutableLiveData<ResponseRegister>()
    val loading=MutableLiveData<Boolean>()

    fun registerUser(bodyRegister: BodyRegister){

        viewModelScope.launch {
            loading.postValue(true)

            val response=registerRepository.registerUser(bodyRegister)
            if(response.isSuccessful){
                responseRegister.postValue(response.body())
            }
            loading.postValue(false)


        }
    }


}