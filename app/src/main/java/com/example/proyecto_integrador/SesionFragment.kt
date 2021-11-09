package com.example.proyecto_integrador

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto_integrador.databinding.ActivityAppBinding
import com.example.proyecto_integrador.databinding.FragmentDetailsBinding
import com.example.proyecto_integrador.databinding.FragmentSesionBinding


class SesionFragment : Fragment() {

    private lateinit var binding: FragmentSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSesionBinding.inflate(inflater, container, false)

        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSesionBinding.bind(view)

        binding.btnCerrarSesion.setOnClickListener {
            //borrando datos
         //   val prefs: SharedPreferences.Editor = get
        }
    }
}