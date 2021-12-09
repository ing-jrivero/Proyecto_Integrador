package com.example.proyecto_integrador.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.proyecto_integrador.MiAplicacion
import com.example.proyecto_integrador.Model.RegistroEntity
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.databinding.ActivityMainBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
   // private val user: UserDataViewModel by activityViewModels()
    var email: String = ""
    var pass: String = ""

    // val TAG = "MainActivity"
    private lateinit var auth: FirebaseAuth
    private val GOOGLE_SING_IN = 100
    private val callbackManager = CallbackManager.Factory.create()
    val botonif = false

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
        bundle.putString("message", "Integracion de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        doAsync {
            val rows = MiAplicacion.database.registroDao().CountRows()
            uiThread {
                if(rows>0){
                 //   skipLogin()

                }
            }
        }




        //  sesion()

        binding.btnRegistrar.setOnClickListener {

            //   Toast.makeText(this, "vamos a obtener datos", Toast.LENGTH_SHORT).show()
            obtenerDatos()
            if (email != "" && pass != "") {
                //     Log.d(TAG, "si entramos al if")
                //          Toast.makeText(this, "si entramos al if", Toast.LENGTH_SHORT).show()
                //     Log.d(TAG, "email = " + email + " + " + "pass = " + pass)
             //         Toast.makeText(this,"email = " + email + " + " + "pass = " + pass,Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            //               Log.d(TAG, "nos dio successful")
                            //              Toast.makeText(this, "nos dio successful", Toast.LENGTH_SHORT).show()
                            openApp(it.result?.user?.email ?: "", ProviderType.Email)
                        } else {
                            //             Log.d(TAG, "nos dio ! successful")
                            //             Toast.makeText(this, "nos dio ! successful", Toast.LENGTH_SHORT).show()


                            showAlert()

                        }

                        //         Toast.makeText(this, "" + it.isSuccessful, Toast.LENGTH_SHORT).show()
                        //         Toast.makeText(this, "" + it.isComplete, Toast.LENGTH_SHORT).show()

                    }


                //    val intent = Intent(this,AppActivity::class.java)

                //  startActivity(intent)
            } else {
                Toast.makeText(this, "ERROR: Complete los campos requeridos", Toast.LENGTH_SHORT).show()
            }

        }

        binding.button.setOnClickListener {
            val intent = Intent(this, AppActivity::class.java).apply {
                putExtra("email", "email")
                putExtra("provider", "provider")
            }
            guardarDatos("email", "provider")
            startActivity(intent)
        }

        binding.btnGoogle.setOnClickListener {
        //    Toast.makeText(this, "google", Toast.LENGTH_SHORT).show()
            //configuracion
            val googleconf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient: GoogleSignInClient = GoogleSignIn.getClient(this, googleconf)

            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)


        }

        binding.btnFacebook.setOnClickListener {

            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            LoginManager.getInstance().registerCallback(callbackManager,
                object :  FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken

                            val credential: AuthCredential =
                                FacebookAuthProvider.getCredential(token.token)
                            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    //         Log.d(TAG,"nos dio successful")

                                     //   FirebaseAuth.getInstance().signOut()
                                         LoginManager.getInstance().logOut()

                                    guardarDatos(it.result?.user?.email ?: "", ProviderType.FACEBOOK.toString())
                                    openApp(it.result?.user?.email ?: "", ProviderType.FACEBOOK)
                                } else {

                                    //          Log.d(TAG,"nos dio ! successful")
                                    showAlert()

                                }
                            }

                        }

                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                        showAlert()
                    }

                })
        }


        binding.btnAcceder.setOnClickListener {
            obtenerDatos()

            if (email != "" && pass != "") {
                //   Log.d(TAG,"si entramos al if")
                //   Toast.makeText(this,binding.etEmail.text.toString(),Toast.LENGTH_SHORT).show()
                //    Toast.makeText(this,binding.etPassword.text.toString(),Toast.LENGTH_SHORT).show()

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            //         Log.d(TAG,"nos dio successful")
                            openApp(it.result?.user?.email ?: "", ProviderType.Email)
                        } else {

                            //          Log.d(TAG,"nos dio ! successful")
                            showAlert()

                        }

                    }

                //  val intent = Intent(this,AppActivity::class.java)

                //    startActivity(intent)
            } else {

                Toast.makeText(this, "ERROR: Complete los campos requeridos", Toast.LENGTH_SHORT).show()
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

    fun skipLogin(){
        val appIntent = Intent(this, AppActivity::class.java).apply {
            //       Toast.makeText(applicationContext,"Seteamos el intent",Toast.LENGTH_SHORT).show()
            //    Log.d(TAG,"Seteamos el intent")
            putExtra("email", "email")
            putExtra("provider", "provider")
        }
        startActivity(appIntent)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callbackManager.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SING_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credential: AuthCredential =
                        GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            //         Log.d(TAG,"nos dio successful")
                                guardarDatos(account.email ?: "", ProviderType.GOOGLE.toString())
                            openApp(account.email ?: "", ProviderType.GOOGLE)
                        } else {

                            //          Log.d(TAG,"nos dio ! successful")
                            showAlert()

                        }
                    }

                }

                }catch(e: ApiException){
                    showAlert()
            }

        }
    }


        private fun sesion() {
            val prefs: SharedPreferences =
                getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
            val emailT: String? = prefs.getString("email", null)
            val providerT: String? = prefs.getString("provider", null)
            if (emailT != null && providerT != null) {
                openApp(emailT, ProviderType.valueOf(providerT))


            }

        }


        fun obtenerDatos() {
            email = binding.etEmail.text.toString()
            pass = binding.etPassword.text.toString()
        //    Toast.makeText(this, email + " + " + pass, Toast.LENGTH_SHORT).show()
        }

        private fun showAlert() {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Error")
            builder.setMessage("Se ha producido un error autenticando al usuario")
            builder.setPositiveButton("Aceptar", null)
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        private fun openApp(email: String, provider: ProviderType) {
            val appIntent = Intent(this, AppActivity::class.java).apply {
                //       Toast.makeText(applicationContext,"Seteamos el intent",Toast.LENGTH_SHORT).show()
                //    Log.d(TAG,"Seteamos el intent")
                putExtra("email", email)
                putExtra("provider", provider.name)
            }
            guardarDatos(email,provider.name)
            //  Log.d(TAG,"vamos a abrir el intent")
            //       Toast.makeText(applicationContext,"vamos a abrir el intent",Toast.LENGTH_SHORT).show()
            startActivity(appIntent)
        }
        fun guardarDatos(e: String,p: String){
            addRegistro(RegistroEntity(email = e,proveedor = p)
            )
        }

    private fun addRegistro(registro: RegistroEntity) {
        doAsync {
  //      CoroutineScope(Dispatchers.IO).launch {
  //      lifecycleScope.launch {
    //        val result = withContext(Dispatchers.IO){MiAplicacion.database.registroDao().addRegistro(registro)}
      //  }
            val id = MiAplicacion.database.registroDao().addRegistro(registro)
            //   val recoveryRegistro = MisNotasApp.database.registroDao().getRegitroById(id)

        }

    }

}


