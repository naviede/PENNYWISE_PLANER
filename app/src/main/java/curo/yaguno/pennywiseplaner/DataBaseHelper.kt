package curo.yaguno.pennywiseplaner

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USUARIO)
        db.execSQL(CREATE_TABLE_GASTOS)
        db.execSQL(CREATE_TABLE_INGRESOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val db2 = this.writableDatabase
        db2.execSQL("DROP TABLE IF EXISTS Usuario")
        db2.execSQL("DROP TABLE IF EXISTS Gastos")
        db2.execSQL("DROP TABLE IF EXISTS Ingresos")
        onCreate(db2)
    }

    fun insertUsuario(Nombre: String, Apellido: String,Email: String, Contraena: String,Usuario: String): Long {
        val db = this.writableDatabase
        val UsuarioValues = ContentValues().apply {
            put("Nombre", Nombre)
            put("Apellido",Apellido)
            put("Email",Email)
            put("Contrasena",Contraena)
            put("Usuario",Usuario)
            put("Saldo",0)
        }
        return db.insert("Usuario", null, UsuarioValues)
    }
    fun comprobarUsuario(Usuario: String): String? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT Contrasena FROM $TABLE_USUARIO WHERE Usuario = ?", arrayOf(Usuario.toString()))

        var Contrasena: String? = null

        if (cursor.moveToFirst()) {
            Contrasena = cursor.getString(cursor.getColumnIndexOrThrow("Contrasena"))
        }

        cursor.close()
        return Contrasena
    }

    companion object {
        private const val DATABASE_NAME = "Pennywise_Planer.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USUARIO = "Usuario"
        private const val TABLE_GASTOS = "Gastos"
        private const val TABLE_INGRESOS = "Ingresos"

        private const val CREATE_TABLE_USUARIO = """
            CREATE TABLE Usuario (
                idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
                Nombre TEXT NOT NULL,
                Apellido TEXT NOT NULL,
                Email TEXT NOT NULL,
                Contrasena TEXT NOT NULL,
                Usuario TEXT NOT NULL,
                Saldo INTEGER NOT NULL
            )
        """

        private const val CREATE_TABLE_GASTOS = """
            CREATE TABLE Gastos (
                idGasto INTEGER PRIMARY KEY AUTOINCREMENT,
                idUsuario INTEGER NOT NULL,
                Concepto TEXT NOT NULL,
                Cantidad TEXT NOT NULL,
                FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario)
            )
        """


        private const val CREATE_TABLE_INGRESOS = """
            CREATE TABLE Ingresos (
                idIngresos INTEGER PRIMARY KEY AUTOINCREMENT,
                idUsuario INTEGER NOT NULL,
                Concepto TEXT NOT NULL,
                Cantidad TEXT NOT NULL,
                FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario)
            )
        """
    }
}