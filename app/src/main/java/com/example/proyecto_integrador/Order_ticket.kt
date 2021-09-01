package com.example.proyecto_integrador

data class Order_ticket(val title: String, val quantity: Int, val price: Float, val Stotal: Double,val image : Int ){

    private var _title : String? = null
    private var _quantity : Int? = null
    private var _price : Float? = null
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