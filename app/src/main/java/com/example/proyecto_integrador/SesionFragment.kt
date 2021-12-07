package com.example.proyecto_integrador

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyecto_integrador.databinding.ActivityAppBinding
import com.example.proyecto_integrador.databinding.FragmentDetailsBinding
import com.example.proyecto_integrador.databinding.FragmentSesionBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class SesionFragment : Fragment() {

    private lateinit var binding: FragmentSesionBinding

    //  private lateinit var userDataViewModel: UserDataViewModel
    // private val userViewModel: UserDataViewModel by viewModels()
    private val user: UserDataViewModel by activityViewModels()

    var L_email = "/"
    var L_proveedor = "/"
    // var primerAcceso = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //   userDataViewModel = ViewModelProvider(this).get(UserDataViewModel::class.java)


    }

    private fun obtenerDatosDB() {
        doAsync {
            var r = MiAplicacion.database.registroDao().getAllRegistros()
            //  Registro = MiAplicacion.database.registroDao().getRegitroById(email)

            uiThread {
                //      setearDatos(r)
                //      clearFocus()

                L_email = r.last().email
                L_proveedor = r.last().proveedor
                user.setEmail(L_email)
                user.setProvider(L_proveedor)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSesionBinding.inflate(inflater, container, false)

        val root: View = binding.root

        if (L_email.equals("/")) {
            obtenerDatosDB()

        } else {


            //primerAcceso = true
        }


        user.email.observe(viewLifecycleOwner, {
            binding.tvEmailSesion.text = "Email: " + it
        })
        user.provider.observe(viewLifecycleOwner, Observer {
            binding.tvProveedorSesion.text = "Proveedor: " + it
        })



        return root

    }
/*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toast.makeText(getActivity(),"entramos al metodo",Toast.LENGTH_SHORT).show()
        if (getArguments() != null) {
            email = getArguments()?.getString("nombre").toString();
            proveedor = getArguments()?.getString("apellido").toString();
            binding.tvEmailSesion.text = email
            binding.tvProveedorSesion.text = proveedor
            Toast.makeText(getActivity(),"recibimos argumentos",Toast.LENGTH_SHORT).show()
        }else {
            email = "";
            proveedor = "";
        }

    }*/


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSesionBinding.bind(view)

        //  email = userDataViewModel.getEmail()
        //  proveedor = userDataViewModel.getProvider()

        //  Log.d("TAG","email obtenido: "+email)
        //  binding.tvEmailSesion.text = "Email: "+email
        //  binding.tvProveedorSesion.text = "Proveedor: "+proveedor

        /*  userViewModel.usuario.observe(viewLifecycleOwner, Observer {
            binding.tvEmailSesion.text = "Email: "+it.email
            binding.tvProveedorSesion.text = "Proveedor: "+it.provider
            Log.d("TAG","email de binding "+it.email)
        })*/

        binding.btnCerrarSesion.setOnClickListener {

            //borrando datos
            //   val prefs: SharedPreferences.Editor = get
            //    findNavController().navigate(R.id.action_navigation_sesion_to_login1Fragment)
            eliminarRegistro()
            activity?.finish()

        }


    }


    private fun eliminarRegistro() {
        doAsync {
            MiAplicacion.database.registroDao().deleteAllRegistros()



        }
    }
}