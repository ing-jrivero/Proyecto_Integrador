package com.example.proyecto_integrador.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.proyecto_integrador.MiAplicacion
import com.example.proyecto_integrador.databinding.FragmentSesionBinding
import com.example.proyecto_integrador.viewmodel.UserDataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class SesionFragment : Fragment() {

    private lateinit var binding: FragmentSesionBinding


    private val user: UserDataViewModel by activityViewModels()

    var L_email = "/"
    var L_proveedor = "/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    private fun obtenerDatosDB() {

        doAsync {
       // CoroutineScope(Dispatchers.IO).launch {
            var r = MiAplicacion.database.registroDao().getAllRegistros()
            //  Registro = MiAplicacion.database.registroDao().getRegitroById(email)
             uiThread {
           // activity?.runOnUiThread {
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSesionBinding.bind(view)



        binding.btnCerrarSesion.setOnClickListener {

            //borrando datos

            eliminarRegistro()
            this.activity?.finish()

        }


    }


    private fun eliminarRegistro() {
        doAsync {
      //  CoroutineScope(Dispatchers.IO).launch {
            MiAplicacion.database.registroDao().deleteAllRegistros()



        }
    }
}