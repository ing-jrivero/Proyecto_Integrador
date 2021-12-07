package com.example.proyecto_integrador.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.proyecto_integrador.AppActivity
import com.example.proyecto_integrador.ProviderType
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.UserDataViewModel
import com.example.proyecto_integrador.databinding.ActivityMainBinding
import com.example.proyecto_integrador.databinding.FragmentLoginBinding
import com.example.proyecto_integrador.databinding.FragmentMenuBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {



   // private var _binding: FragmentLoginBinding? = null
    lateinit var binding: FragmentLoginBinding
     private val user: UserDataViewModel by activityViewModels()
    var email: String = ""
    var pass: String = ""

    // val TAG = "MainActivity"
    private lateinit var auth: FirebaseAuth
    private val GOOGLE_SING_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {


        // Thread.sleep(1000)

      //  setTheme(R.style.AppTheme)
        auth = Firebase.auth
        super.onCreate(savedInstanceState)

        // this.supportActionBar?.hide()
        binding = FragmentLoginBinding.inflate(layoutInflater)
       // setContentView(binding.root)


        // Obtain the FirebaseAnalytics instance.
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(requireActivity())
        val bundle = Bundle()
        bundle.putString("message", "Integracion de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        //  sesion()



        binding.btnRegistrar1.setOnClickListener {

            //   Toast.makeText(this, "vamos a obtener datos", Toast.LENGTH_SHORT).show()
            obtenerDatos()
            if (email != "" && pass != "") {
                //     Log.d(TAG, "si entramos al if")
                //          Toast.makeText(this, "si entramos al if", Toast.LENGTH_SHORT).show()
                //     Log.d(TAG, "email = " + email + " + " + "pass = " + pass)
                //      Toast.makeText(this,"email = " + email + " + " + "pass = " + pass,Toast.LENGTH_SHORT).show()
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
               // Toast.makeText(getActivity(), "ERROR: Complete los campos requeridos", Toast.LENGTH_SHORT).show()
            }

        }

        binding.button1.setOnClickListener {
            /*val intent = Intent(this, AppActivity::class.java).apply {
                putExtra("email", "email")
                putExtra("provider", "provider")
            }*/

            guardarDatos("email", "provider")
         //   findNavController().navigate(R.id.action_login1Fragment_to_navigation_menu)
            /*startActivity(intent)*/
        }

        binding.btnGoogle1.setOnClickListener {
            //    Toast.makeText(this, "google", Toast.LENGTH_SHORT).show()
            //configuracion
            val googleconf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient: GoogleSignInClient = GoogleSignIn.getClient(getActivity(), googleconf)

            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)


        }


        binding.btnAcceder1.setOnClickListener {
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

           //     Toast.makeText(getActivity(), "ERROR: Complete los campos requeridos", Toast.LENGTH_SHORT).show()
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

}
}
*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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

                            guardarDatos(account.email ?: "", ProviderType.GOOGLE.toString())
                          //  openApp(account.email ?: "", ProviderType.GOOGLE)
                     //       findNavController().navigate(R.id.action_login1Fragment_to_navigation_menu)
                        } else {


                            showAlert()

                        }
                    }

                }

            }catch(e: ApiException){
                showAlert()
            }

        }
    }


   /* private fun sesion() {
        val prefs: SharedPreferences =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val emailT: String? = prefs.getString("email", null)
        val providerT: String? = prefs.getString("provider", null)
        if (emailT != null && providerT != null) {
            openApp(emailT, ProviderType.valueOf(providerT))


        }

    }*/


    fun obtenerDatos() {
        email = binding.etEmail1.text.toString()
        pass = binding.etPassword1.text.toString()
     //   Toast.makeText(getActivity(), email + " + " + pass, Toast.LENGTH_SHORT).show()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun openApp(email: String, provider: ProviderType) {
        val appIntent = Intent(getActivity(), AppActivity::class.java).apply {

            putExtra("email", email)
            putExtra("provider", provider.name)
        }
         startActivity(appIntent)
    }
    fun guardarDatos(e: String,p: String){
        user.setEmail(e)
        user.setProvider(p)
     //   Log.d("TAG","e: "+user.email.value.toString()+" p: "+user.provider.value.toString())

    }

}
