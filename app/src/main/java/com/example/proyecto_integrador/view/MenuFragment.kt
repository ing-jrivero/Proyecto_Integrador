package com.example.proyecto_integrador.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyecto_integrador.Model.Order_info
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.databinding.FragmentMenuBinding
import com.google.firebase.firestore.FirebaseFirestore

class MenuFragment : Fragment() {

 //   private lateinit var menuViewModel: MenuViewModel
    private var _binding: FragmentMenuBinding? = null
    private val db = FirebaseFirestore.getInstance()




    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()
        _binding = FragmentMenuBinding.bind(view)
        binding!!.ibH01.setOnClickListener(){

          //  bundle.putParcelable("Order", Order_info("Chipotle King",119.99,1,"Preparada con dos carnes de res a la parrilla, jugosos jitomates, lechuga recién cortada, tocino, nuevo aderezo de chipotle y aros de cebolla crujientes, sobre un pan suave con ajonjolí. "))
            db.collection("productos").document("Chipotle King").get().addOnSuccessListener {
          //      val  titulo = it.get("titulo") as String
          //      val precio = it.get("precio") as Double
         //       val imagen = (it.get("imagen") as Long).toInt()
         //       val descripcion = it.get("descripcion") as String

                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))

         //       bundle.putParcelable("Order",Order_info(titulo,precio,imagen,descripcion))
              //  bundle.putParcelable("Order", Order_info("Chipotle King",119.99,1,"Preparada con dos carnes de res a la parrilla, jugosos jitomates, lechuga recién cortada, tocino, nuevo aderezo de chipotle y aros de cebolla crujientes, sobre un pan suave con ajonjolí. "))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }



        }
        binding!!.ibH02.setOnClickListener(){

          //  bundle.putParcelable("Order", Order_info("Long Jalapeño",89.99,2,"Carne de res a la parrilla, queso americano, aros de cebolla, lechuga y picositos jalapeños"))
            db.collection("productos").document("Long Jalapeño").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }



        }
        binding!!.ibH03.setOnClickListener(){
          //  bundle.putParcelable("Order", Order_info("Long Rodeo",89.99,3,"Carne de res a la parrilla, queso americano, aros de cebolla crujientes, salsa BBQ"))
            db.collection("productos").document("Long Rodeo").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibH04.setOnClickListener(){
         //   bundle.putParcelable("Order", Order_info("Stacker Quitutuple",149.99,4,"La hamburguesa más grande de todos los tiempos llega a tus dos manos.Con tocino crujiente, queso derretido, salsa Stacker y con 5 carnes"))
            db.collection("productos").document("Stacker Quitutuple").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibC01.setOnClickListener(){
          //  bundle.putParcelable("Order", Order_info("Papas a la Francesa",24.99,5,"Más deliciosas que nunca, nuestras famosas papas a la francesa son de corte regular, saladito, doradas por fuera y suaves por dentro."))
            db.collection("productos").document("Papas a la Francesa").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibC02.setOnClickListener(){
         //   bundle.putParcelable("Order", Order_info("Papas Supremas",49.99,6,"Descubre las nuevas papas supremas bañadas en salsa sabor a queso con trocitos sabor tocino y cebollitas crujientes!"))
            db.collection("productos").document("Papas Supremas").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibC03.setOnClickListener(){
          //  bundle.putParcelable("Order", Order_info("Ensalada con Nuggets",59.99,7,"Deliciosa combinación de lechugas y vegetales frescos con una combinación de salsas de la casa. "))
            db.collection("productos").document("Ensalada con Nuggets").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibC04.setOnClickListener(){
           // bundle.putParcelable("Order", Order_info("Ensalada Cranberry",89.99,8,"Deliciosa combinación de lechugas, con rodajas de piña y vegetales"))
            db.collection("productos").document("Ensalada Cranberry").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibC05.setOnClickListener(){
           // bundle.putParcelable("Order", Order_info("Juego de Naranja",19.99,9,"¡Bebida refrescante!"))
            db.collection("productos").document("Juego de Naranja").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibC06.setOnClickListener(){
           // bundle.putParcelable("Order", Order_info("Agua Epura",14.99,10,"¡Acompaña tus combos con una botella de Epura!"))
            db.collection("productos").document("Agua Epura").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibC07.setOnClickListener(){
           // bundle.putParcelable("Order", Order_info("Cafe Dunkin",24.99,11,"De grano suave, nuestro café con notas dulces que te ofrecen un sabor suave, equilibrado y perfecto para comenzar el día."))
            db.collection("productos").document("Cafe Dunkin").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }
        binding!!.ibC08.setOnClickListener(){
         //   bundle.putParcelable("Order", Order_info("Refresco Pepsi",24.99,12,"Acompaña tus COMBOS con un refresco grande del sabor que tú quieras!"))
            db.collection("productos").document("Refresco Pepsi").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibP01.setOnClickListener(){
          //  bundle.putParcelable("Order", Order_info("Oreo Shake Thumb",49.99,13,"Nuestra rica malteada sabor vainilla con pedazos de galleta Oreo"))
            db.collection("productos").document("Oreo Shake Thumb").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }
        binding!!.ibP02.setOnClickListener(){
          //  bundle.putParcelable("Order", Order_info("King Fusion KitKat",49.99,14,"Delicioso sundae de vainilla, mezclado con trocitos de chocolate kitkat® para un sabor único."))
            db.collection("productos").document("King Fusion KitKat").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibP03.setOnClickListener(){
          //  bundle.putParcelable("Order", Order_info("King Fusion Emperador",74.99,15,"Delicioso sundae de vainilla, mezclado con trocitos de galleta Emperador® para un sabor único."))
            db.collection("productos").document("King Fusion Emperador").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibP04.setOnClickListener(){
           // bundle.putParcelable("Order", Order_info("King Fusion Oreo",49.99,16,"Fusión de nuestro delicioso helado sabor vainilla y galleta Oreo"))
            db.collection("productos").document("King Fusion Oreo").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibP05.setOnClickListener(){
           // bundle.putParcelable("Order", Order_info("Pay de Manzana",39.99,17,"Con un relleno de manzana y envuelto en costra de hojaldre, nuestro Pay de Manzana se se sirve calientito, recién salido de la cocina."))
            db.collection("productos").document("Pay de Manzana").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }

        binding!!.ibP06.setOnClickListener(){
           // bundle.putParcelable("Order", Order_info("Pay HERSHEY'S",49.99,18,"Da la bienvenida a nuestro Pay HERSHEY'S® Primero, una corteza de chocolate crujiente y una parte de relleno cremoso de chocolate, adornado con un delicioso relleno de chocolate HERSHEY'S® y chispas de chocolate."))
            db.collection("productos").document("Pay HERSHEY'S").get().addOnSuccessListener {
                bundle.putParcelable("Order", Order_info(it.get("titulo") as String,it.get("precio") as Double,(it.get("imagen") as Long).toInt(),it.get("descripcion") as String))
                findNavController().navigate(R.id.action_navigation_menu_to_fragmentDetails,bundle)
            }


        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}