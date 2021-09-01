package com.example.proyecto_integrador.ui.ticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_integrador.CustomAdapter
import com.example.proyecto_integrador.OrderViewModel
import com.example.proyecto_integrador.Order_ticket
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {

  //  private lateinit var ticketViewModel: TicketViewModel
    private lateinit var binding: FragmentTicketBinding
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private lateinit var list_orders : MutableList<Order_ticket>



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

    /* override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }*/
}