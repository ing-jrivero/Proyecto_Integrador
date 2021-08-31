package com.example.proyecto_integrador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
//import com.example.proyecto_integrador.databinding.ActivityMainBinding
import com.example.proyecto_integrador.databinding.FragmentDetailsBinding
//import android.R

//import com.example.proyecto_integrador.databinding.FragmentTicketBinding
//import android.R.color





class FragmentDetails : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val sharedViewModel: OrderViewModel by activityViewModels()

    private var orden : Order_info? = null
//    private var comprar : Button? = null
    private var titulo : String? = null
    private var descripcion : String? = null
    private var precio : Double? = null
    private var cantidad : TextView? = null
    private var menos : ImageButton? = null
    private var mas : ImageButton? = null
    private var Cod_imagen : Int? = null
//    private var imagen : ImageView? = null
    private var img : Int = 0
    private var cant = 1
    private var sendOrder: Order_ticket? = null

   // private val uri : String = "@drawable/chipotleking"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)




        arguments?.let{

            orden = it.getParcelable("Order")
            titulo = orden?.titulo
            descripcion = orden?.descripcion
            precio = orden?.precio
            Cod_imagen = orden?.imagen


        }


        binding.tvTitulo.text = titulo
        binding.tvPrecio.text = "$"+precio.toString()
        Selecion_imagen()
        binding.ivImagen.setImageResource(img)
        binding.tvDescripcion.text = descripcion

        binding.ibMenos.setOnClickListener(){
            if(cant > 1)
                cant--
            binding.tvCantidad.text=cant.toString()
            updatePrice()
        }
        binding.ibMas.setOnClickListener(){
            cant++
            binding.tvCantidad.text=cant.toString()
            updatePrice()
        }
        binding.btnComprar.setOnClickListener(){
            sendOrder = Order_ticket(titulo.toString(),cant,precio!!,(cant* precio!!),img)
            sharedViewModel.addOrder(sendOrder!!)
         //   Toast.makeText(getActivity(),sendOrder.toString(),Toast.LENGTH_SHORT).show()
            Toast.makeText(getActivity(),"Producto Agregado Al Recibo",Toast.LENGTH_SHORT).show()


        }

    }

    private fun updatePrice(){
        var numero : Float? = null
        numero=(cant * precio!!).toFloat()
        binding.tvPrecio.text="$"+numero.toString()
    }


    private fun Selecion_imagen(){
        when(Cod_imagen){
            0 -> img = R.drawable.chipotleking
            1 -> img = R.drawable.chipotleking
            2 -> img = R.drawable.long_jalapeno
            3 -> img = R.drawable.long_rodeo
            4 -> img = R.drawable.stacker_quintuple
            5 -> img = R.drawable.papas_fritas_img_grid
            6 -> img = R.drawable.bk_papas_supremas_atumanera
            7 -> img = R.drawable.nuggetensalada_grande
            8 -> img = R.drawable.bk_web_ensalada_cranberry
            9 -> img = R.drawable.jugo
            10 -> img = R.drawable.burgerking_agua_epura
            11 -> img = R.drawable.cafe_dunkin
            12 -> img = R.drawable.burgerking_pepsi
            13 -> img = R.drawable.oreo_shake_thumb
            14 -> img = R.drawable.bk_web_kingfusionkitkat
            15 -> img = R.drawable.king_fusion_emperador
            16 -> img = R.drawable.oreo_shake_thumb
            17 -> img = R.drawable.pay
            18 -> img = R.drawable.pastel


        }
    }



}