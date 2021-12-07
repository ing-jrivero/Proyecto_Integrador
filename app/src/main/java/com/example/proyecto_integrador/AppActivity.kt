package com.example.proyecto_integrador

import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyecto_integrador.databinding.ActivityAppBinding
import com.example.proyecto_integrador.ui.menu.MenuViewModel

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import androidx.fragment.app.activityViewModels
import com.example.proyecto_integrador.UserDataViewModel

enum class ProviderType{
    Email,
    GOOGLE,
    FACEBOOK,

}

class AppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppBinding
   // private lateinit var userDataViewModel: UserDataViewModel
   // private val user: UserDataViewModel by viewModels()
   //private val user: UserDataViewModel by activityViewModels()
    private lateinit var navController: NavController
    var email = ""
    var provider= ""
    override fun onCreate(savedInstanceState: Bundle?) {


        // Thread.sleep(1000)

        setTheme(R.style.AppTheme)


       // title = "Proyecto integrador"

        super.onCreate(savedInstanceState)
        // this.supportActionBar?.hide()
       // userDataViewModel = ViewModelProvider(this).get(UserDataViewModel::class.java)
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the FirebaseAnalytics instance.
     //   val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    //    val bundle = Bundle()
    //    bundle.putString("message","Integracion de Firebase completa")
    //    analytics.logEvent("InitScreen",bundle)
     //   binding.navView.visibility = View.GONE

        val bundle: Bundle? = intent.extras
        email = bundle?.getString("email")!!
        provider = bundle?.getString("provider")!!
      //  Toast.makeText(this,"e = "+email+" + "+"P = "+provider,Toast.LENGTH_SHORT).show()

        //Guardamndo Datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()
     //   userDataViewModel.setEmail(email)
     //   userDataViewModel.setProvider(provider)
     //   Log.d("TAG","Email seteado: "+userDataViewModel.getEmail())
     //   userDataViewModel.setUsuario(email,provider)
      //  Log.d("TAG","Email seteado: "+userDataViewModel.usuario.value)


/*
        val bundleS = Bundle()
        bundleS.putString("email", value1)
        bundleS.putString("provedor", value2)*/

        val navView: BottomNavigationView = binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_menu, R.id.navigation_map, R.id.navigation_ticket,R.id.navigation_sesion
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)




    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun borrardatos(){
        val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()

        FirebaseAuth.getInstance().signOut()
        onBackPressed()
    }

}