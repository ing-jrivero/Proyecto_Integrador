package com.example.proyecto_integrador.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto_integrador.Model.RegistroDao
import com.example.proyecto_integrador.Model.RegistroEntity
import kotlinx.coroutines.launch

class UserDataViewModel: ViewModel() {



    private val _email = MutableLiveData<String>("Ninguno")
    val email: LiveData<String> = _email

    private val _provider = MutableLiveData<String>("Ninguno")
    val provider: LiveData<String> = _provider

    fun setEmail(email: String){
  //      Log.d("TAG","guardar email "+email)
        _email.value = email
    //    Log.d("TAG","valor email "+_email.value)

    }
    fun setProvider(provider: String){
        _provider.value = provider
    }



    fun getEmail(): String{
     //   Log.d("TAG","email es "+_email.value)
        return _email.value.toString()
    }
    fun getProvider(): String{
        return provider.value.toString()
    }
}