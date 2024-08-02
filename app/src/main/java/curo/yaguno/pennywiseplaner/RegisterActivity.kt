package curo.yaguno.pennywiseplaner
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper:DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val NombreO = findViewById<EditText>(R.id.et_Nombres)
        val ApellidoO = findViewById<EditText>(R.id.et_Apellidos)
        val EmailO = findViewById<EditText>(R.id.et_Email)
        val ContrasenaO = findViewById<EditText>(R.id.et_Contraseña)
        val UsuarioO = findViewById<EditText>(R.id.et_Usuario)
        val registerButton = findViewById<ImageButton>(R.id.bt_home)
        dbHelper = DataBaseHelper(this)

        registerButton.setOnClickListener {
            val Nombre = NombreO.text.toString()
            val Apellido = ApellidoO.text.toString()
            val Email = EmailO.text.toString()
            val Contrasena = ContrasenaO.text.toString()
            val Usuario = UsuarioO.text.toString()

            if(Nombre.isNotEmpty() && Apellido.isNotEmpty() && Email.isNotEmpty() && Contrasena.isNotEmpty() && Usuario.isNotEmpty()){
                dbHelper.insertUsuario(Nombre,Apellido,Email,Contrasena,Usuario)
                finish()
            }
            else{
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

}