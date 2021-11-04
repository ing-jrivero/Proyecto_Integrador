package com.example.proyecto_integrador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_integrador.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var email: String = ""
    var pass: String = ""
    val TAG = "MainActivity"
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {


        // Thread.sleep(1000)

        setTheme(R.style.AppTheme)
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
       // this.supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Obtain the FirebaseAnalytics instance.
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Integracion de Firebase completa")
        analytics.logEvent("InitScreen",bundle)


        binding.btnRegistrar.setOnClickListener {
            obtenerDatos()
            if(email !="" && pass!=""){
                Log.d(TAG,"si entramos al if")
         /*       FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        Log.d(TAG,"nos dio successful")
                        openApp(it.result?.user?.email ?: "",ProviderType.BASIC)
                    }else{
                        Log.d(TAG,"nos dio ! successful")


                        showAlert()

                    }



                }*/


                auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            openApp(email,ProviderType.BASIC)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            showAlert()

                        }
                    }





            //    val intent = Intent(this,AppActivity::class.java)

              //  startActivity(intent)
            }else{
                Toast.makeText(this,"ERROR: Complete los campos requeridos",Toast.LENGTH_SHORT).show()
            }

        }

        binding.button.setOnClickListener {
              val intent = Intent(this,AppActivity::class.java)
                startActivity(intent)
        }


        binding.btnAcceder.setOnClickListener {
            obtenerDatos()

            if(email !="" && pass!=""){
                Log.d(TAG,"si entramos al if")
                Toast.makeText(this,binding.etEmail.text.toString(),Toast.LENGTH_SHORT).show()
                Toast.makeText(this,binding.etPassword.text.toString(),Toast.LENGTH_SHORT).show()

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            Log.d(TAG,"nos dio successful")
                            openApp(it.result?.user?.email ?: "",ProviderType.BASIC)
                        }else{

                            Log.d(TAG,"nos dio ! successful")
                            showAlert()

                        }

                }

              //  val intent = Intent(this,AppActivity::class.java)

            //    startActivity(intent)
            }else{

                Toast.makeText(this,"ERROR: Complete los campos requeridos",Toast.LENGTH_SHORT).show()
            }

        }


    }
   /* override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/
  /*
   public override fun onStart() {
       super.onStart()
       // Check if user is signed in (non-null) and update UI accordingly.
       val currentUser = auth.currentUser
       if(currentUser != null){
           Log.d(TAG,"entramos a curen != null")
       }
   }
*/




    fun obtenerDatos(){
        email = binding.etEmail.text.toString()
        pass = binding.etPassword.text.toString()
       Toast.makeText(this,email+" + "+pass,Toast.LENGTH_SHORT).show()
    }

    fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun openApp(email: String, provider: ProviderType){
        val appIntent = Intent(this,AppActivity::class.java).apply {
            Log.d(TAG,"Seteamos el intent")
            putExtra("email",email)
            putExtra("provider", provider.name)
        }
        Log.d(TAG,"vamos a abrir el intent")
        startActivity(appIntent)
    }


}