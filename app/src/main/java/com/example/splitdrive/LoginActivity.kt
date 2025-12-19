package com.example.splitdrive

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth //
import com.google.firebase.ktx.Firebase //

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var btnGoogle: Button
    private lateinit var txtEstado: TextView

    // Launcher para recibir el resultado del intent de Google
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val task: Task<com.google.android.gms.auth.api.signin.GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val idToken = account.idToken
                if (idToken != null) {
                    firebaseAuthWithGoogle(idToken)
                } else {
                    Toast.makeText(this, "No se pudo obtener el token de Google",
                        Toast.LENGTH_SHORT).show()
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Error al iniciar sesión con Google: ${e.message}",
                    Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Inicio de sesión cancelado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // LAYOUT DE LOGIN
        setContentView(R.layout.activity_login)

        // 1. Referencias a las vistas
        btnGoogle = findViewById(R.id.btnGoogle)
        txtEstado = findViewById(R.id.txtEstado)

        // 2. Inicializar Firebase Auth
        auth = Firebase.auth

        // 3. Configurar Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // 4. Listener del botón
        btnGoogle.setOnClickListener {
            signInWithGoogle()
        }

        // 5. Verificar estado de sesión y navegar si es necesario
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToMain()
        } else {
            updateUI(null)
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Autenticación exitosa", Toast.LENGTH_SHORT).show()
                    navigateToMain() // 💡 NAVEGAR TRAS EL LOGIN EXITOSO
                } else {
                    Toast.makeText(this, "Fallo en autenticación Firebase",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun navigateToMain() {
        // Lógica de navegación limpia
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            txtEstado.text = "Estado: Autenticado como ${user.displayName} (${user.email})"
        } else {
            txtEstado.text = "Estado: No autenticado"
        }
    }
}