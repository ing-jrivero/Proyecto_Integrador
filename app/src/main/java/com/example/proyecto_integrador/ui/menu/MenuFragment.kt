package com.example.proyecto_integrador.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto_integrador.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var menuViewModel: MenuViewModel
    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        menuViewModel =
            ViewModelProvider(this).get(MenuViewModel::class.java)

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView_hamburguesas: TextView = binding.tvHamburguesas
        menuViewModel.text_hamburguesas.observe(viewLifecycleOwner, Observer {
            textView_hamburguesas.text = it
        })
        val textView_complementos: TextView = binding.tvComplementos
        menuViewModel.text_complementos.observe(viewLifecycleOwner, Observer {
            textView_complementos.text = it
        })
        val textView_postres: TextView = binding.tvPostres
        menuViewModel.text_postres.observe(viewLifecycleOwner, Observer {
            textView_postres.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}