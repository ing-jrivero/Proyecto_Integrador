package com.example.proyecto_integrador


import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.android.material.internal.ContextUtils.getActivity

import java.text.NumberFormat

private var total_Price : Double? = null
private var ordenes : MutableList<Order_ticket>? = null
private var objetoVacio = Order_ticket("Lista Vacia",0,0.toFloat(),0.0,0)




class OrderViewModel : ViewModel() {

    private val _order = MutableLiveData<Order_ticket>()
    val order: LiveData<Order_ticket> = _order

    private val _title = MutableLiveData<String>()
    val tilte: LiveData<String> = _title

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _price = MutableLiveData<Float>()
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }




    init{
        resetOrder()
    }

    fun setQuantity(numberOfProducts: Int){
        _quantity.value = numberOfProducts
       // updatePrice()
    }
    fun setTitle(titleOfProducts:String){
        _title.value = titleOfProducts
    }
    fun setPrice(priceOfProducts: Float){
        _price.value = priceOfProducts
      //  updatePrice()
    }


    fun resetOrder(){
        ordenes?.clear()
        ordenes= mutableListOf(objetoVacio)

    }


    fun addOrder(nOrd:Order_ticket){

            ordenes!!.add(nOrd)
      // Log.d("OrderViewModel","OS:"+ ordenes.toString() )

    }

     fun getOrders(): MutableList<Order_ticket> {
        return ordenes!!
    }

    /*private fun updatePrice(){
        var calculatedPrice = (quantity.value ?: 0)* total_Price!!
        _price.value = calculatedPrice
    }*/

    fun isEmpty(): Boolean{
        return ordenes.isNullOrEmpty()
    }

}