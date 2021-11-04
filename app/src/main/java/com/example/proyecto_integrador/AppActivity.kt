package com.example.proyecto_integrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyecto_integrador.databinding.ActivityAppBinding

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics

enum class ProviderType{
    BASIC
}

class AppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {


        // Thread.sleep(1000)

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        // this.supportActionBar?.hide()
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the FirebaseAnalytics instance.
     //   val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    //    val bundle = Bundle()
    //    bundle.putString("message","Integracion de Firebase completa")
    //    analytics.logEvent("InitScreen",bundle)



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


}