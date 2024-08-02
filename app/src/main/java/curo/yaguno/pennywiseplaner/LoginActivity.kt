package curo.yaguno.pennywiseplaner

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper:DataBaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        enableEdgeToEdge()

        dbHelper = DataBaseHelper(this)
        val UsuarioO = findViewById<EditText>(R.id.et_Email)
        val ContrasenaO = findViewById<EditText>(R.id.et_Contraseña)
        val registerButton = findViewById<TextView>(R.id.ingresar)

        findViewById<TextView>(R.id.new_user).setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val Usuario = UsuarioO.text.toString()
            val Contrasena = ContrasenaO.text.toString()
            val contra = dbHelper.comprobarUsuario(Usuario)

            if(contra == Contrasena){
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("Usuario",Usuario)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Datos invalidos", Toast.LENGTH_SHORT).show()
            }
        }


    }
}