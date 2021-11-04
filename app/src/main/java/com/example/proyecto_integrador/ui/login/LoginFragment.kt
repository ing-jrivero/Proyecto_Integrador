package com.example.proyecto_integrador.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.databinding.FragmentLoginBinding
import com.example.proyecto_integrador.databinding.FragmentMenuBinding
import com.google.firebase.firestore.FirebaseFirestore


class LoginFragment : Fragment() {



    private var _binding: FragmentLoginBinding? = null
    private val db = FirebaseFirestore.getInstance()
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_login, container, false)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()
        _binding = FragmentLoginBinding.bind(view)

        binding.btnIniciarSesion.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_navigation_menu)
        }

        binding.tvRegistarse.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }





    }
}