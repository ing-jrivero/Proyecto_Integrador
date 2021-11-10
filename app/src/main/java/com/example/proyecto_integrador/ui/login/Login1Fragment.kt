package com.example.proyecto_integrador.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.proyecto_integrador.AppActivity
import com.example.proyecto_integrador.ProviderType
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.UserDataViewModel
import com.example.proyecto_integrador.databinding.FragmentLogin1Binding
import com.example.proyecto_integrador.databinding.FragmentLoginBinding
import com.example.proyecto_integrador.databinding.FragmentSignUpBinding
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


class Login1Fragment : Fragment() {

    lateinit var binding: FragmentLogin1Binding
    private val user: UserDataViewModel by activityViewModels()
    var email: String = ""
    var pass: String = ""

    // val TAG = "MainActivity"
    private lateinit var auth: FirebaseAuth
    private val GOOGLE_SING_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = FragmentLoginBinding.inflate(layoutInflater)

        // Obtain the FirebaseAnalytics instance.
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(requireActivity())
        val bundle = Bundle()
        bundle.putString("message", "Integracion de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login1, container, false)
        binding = FragmentLogin1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()
        binding = FragmentLogin1Binding.bind(view)

        binding.buttonp1.setOnClickListener {
            /*val intent = Intent(this, AppActivity::class.java).apply {
                putExtra("email", "email")
                putExtra("provider", "provider")
            }*/

            guardarDatos("email", "provider")
            findNavController().navigate(R.id.action_login1Fragment_to_navigation_menu)
            /*startActivity(intent)*/
        }

        binding.btnGooglep1.setOnClickListener {
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
                            findNavController().navigate(R.id.action_login1Fragment_to_navigation_menu)
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
        Log.d("TAG","e: "+user.email.value.toString()+" p: "+user.provider.value.toString())

    }

}