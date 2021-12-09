package com.example.proyecto_integrador.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_integrador.CustomAdapter

import com.example.proyecto_integrador.Model.Order_ticket
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.databinding.FragmentTicketBinding
import com.example.proyecto_integrador.viewmodel.OrderViewModel

class TicketFragment : Fragment() {


    private lateinit var binding: FragmentTicketBinding
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private lateinit var list_orders : MutableList<Order_ticket>

    var lista = mutableListOf("")



    var totales = mutableListOf(0.0)
    var total : Float? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        list_orders=sharedViewModel.getOrders()




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentTicketBinding.inflate(inflater, container, false)

        val root: View = binding.root


        list_orders=sharedViewModel.getOrders()
        if(list_orders.size>1){
            if(list_orders[0].price == 0.0){
                list_orders.removeAt(0)
            }

            }


            val recyclerView = root.findViewById<RecyclerView>(R.id.V_recyclerView)
            val adapter = CustomAdapter(list_orders)

            recyclerView.layoutManager = LinearLayoutManager(getContext())
            recyclerView.adapter = adapter





        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTicketBinding.bind(view)
        calcularTotal()
        binding.tvTotal.text="Total:  $ "+total.toString()


        binding.btnPagar.setOnClickListener(){
            if(list_orders[0].price != 0.0){
                sendOrder()
            }else{
                Toast.makeText(getActivity(),"@String/txt_lista_vacia",Toast.LENGTH_SHORT).show()

            }

        }

        binding.btnCancelar.setOnClickListener(){
            sharedViewModel.resetOrder()
            findNavController().navigate(R.id.action_navigation_ticket_to_navigation_menu)

        }





    }

    private fun calcularTotal(){
        obtenerPrecios()
        total=totales.sum().toFloat()



    }

    private fun obtenerPrecios(){
        for(item in list_orders){
            totales.add( item.Stotal)

        }
    }



fun sendOrder() {

    prepararPedido()
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.nueva_orden_subj))
        .putExtra(Intent.EXTRA_TEXT, lista.toString().replace('[',' ').replace(']',' '))
        .putExtra(Intent.EXTRA_EMAIL,"burgerK_Shop@gmail.com")
    if(activity?.packageManager?.resolveActivity(intent, 0) != null) {
        startActivity(intent)

    }

}

    fun prepararPedido(){
        for (item in list_orders){

                lista!!.add("\n \n* Nombre: ${item.title} Cantidad: ${item.quantity} Precio: ${item.price} SubTotal: ${item.Stotal}")


        }
        lista.add("\n \n* Total: $total")

        lista.removeAt(0)
    }



}