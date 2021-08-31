package com.example.proyecto_integrador.ui.ticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_integrador.CustomAdapter
import com.example.proyecto_integrador.OrderViewModel
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.databinding.FragmentDetailsBinding
import com.example.proyecto_integrador.databinding.FragmentTicketBinding

class TicketFragment : Fragment() {

  //  private lateinit var ticketViewModel: TicketViewModel
    private lateinit var binding: FragmentTicketBinding



    val totales = doubleArrayOf(800.0, 23.0, 3.5, 2223.3)
    var total : Float? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentTicketBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val recyclerView = root.findViewById<RecyclerView>(R.id.V_recyclerView)
        val adapter = CustomAdapter()

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
        total=totales.sum().toFloat()



    }

    /* override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }*/
}