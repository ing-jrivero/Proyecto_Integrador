package com.example.proyecto_integrador

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat

private var total_Price : Double? = null
private var ordenes : MutableList<Order_ticket>? = null

//private val nombre : Drawable =
    private val uri : String = "@drawable/imagen"
    /*String uri = "@drawable/imagen";
int imageResource = getResources().getIdentifier(uri, null, getPackageName());
Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
imageView.setImageDrawable(imagen);*/



class OrderViewModel : ViewModel() {

    private val _order = MutableLiveData<Order_ticket>()
    val order: LiveData<Order_ticket> = _order

    private val _title = MutableLiveData<String>()
    val tilte: LiveData<String> = _title

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _price = MutableLiveData<Double>()
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
    fun setPrice(priceOfProducts: Double){
        _price.value = priceOfProducts
      //  updatePrice()
    }


    fun resetOrder(){
     //   _quantity.value = 0
  //      _flavor.value = ""
    //    _date.value = dateOptions[0]
     //   _price.value = 0.0
    }

    fun addOrder(nOrd:Order_ticket){
        ordenes?.add(nOrd)

    }

     fun getOrders(): MutableList<Order_ticket> {
        return ordenes!!
    }

    private fun updatePrice(){
        var calculatedPrice = (quantity.value ?: 0)* total_Price!!
        _price.value = calculatedPrice
    }


}