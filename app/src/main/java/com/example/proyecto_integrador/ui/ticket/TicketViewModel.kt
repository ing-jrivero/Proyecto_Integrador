package com.example.proyecto_integrador.ui.ticket

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicketViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}
/*
fun sendOrder() {
    val numberOfCupcakes = sharedViewModel.quantity.value?:0
    val orderSummary = getString(
        R.string.order_details,
        resources.getQuantityString(R.plurals.cupcakes, numberOfCupcakes, numberOfCupcakes),
        sharedViewModel.flavor.value.toString(),
        sharedViewModel.date.value.toString(),
        sharedViewModel.price.value.toString()

    )
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
        .putExtra(Intent.EXTRA_TEXT, orderSummary)
        .putExtra(Intent.EXTRA_EMAIL,"Cupcake.Shop@gmail.com")
    if(activity?.packageManager?.resolveActivity(intent, 0) != null) {
        startActivity(intent)

    }

}*/

