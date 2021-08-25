package com.example.proyecto_integrador.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel : ViewModel() {

    private val _text_hamburguesas = MutableLiveData<String>().apply {
        value = "Hamburguesas"
    }
    val text_hamburguesas: LiveData<String> = _text_hamburguesas

    private val _text_complementos = MutableLiveData<String>().apply {
        value = "Complementos"
    }
    val text_complementos: LiveData<String> = _text_complementos

    private val _text_postres = MutableLiveData<String>().apply {
        value = "Postres"
    }
    val text_postres: LiveData<String> = _text_postres
}