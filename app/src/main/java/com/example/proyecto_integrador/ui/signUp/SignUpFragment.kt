package com.example.proyecto_integrador.ui.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.databinding.FragmentLoginBinding
import com.example.proyecto_integrador.databinding.FragmentSignUpBinding
import com.google.firebase.firestore.FirebaseFirestore


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val db = FirebaseFirestore.getInstance()
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()
        _binding = FragmentSignUpBinding.bind(view)

      binding.btnRegistarse.setOnClickListener {
          findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
      }





    }


}