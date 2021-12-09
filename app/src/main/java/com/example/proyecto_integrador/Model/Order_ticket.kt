package com.example.proyecto_integrador.Model

data class Order_ticket(val title: String, val quantity: Int, val price: Double, val Stotal: Double,val image : Int ){

    private var _title : String? = null
    private var _quantity : Int? = null
    private var _price : Double? = null
    private var _Stotal : Double? = null
    private var _image : Int? = null

    init {
        _title = title
        _quantity = quantity
        _price = price
        _Stotal = Stotal
        _image = image

    }

}