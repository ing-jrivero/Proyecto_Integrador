package com.example.proyecto_integrador

import android.app.Application
import androidx.room.Room
import com.example.proyecto_integrador.Model.RegistroDatabase

class MiAplicacion: Application() {
    companion object{
        lateinit var database: RegistroDatabase
    }

    override fun onCreate() {
        super.onCreate()

        MiAplicacion.database = Room.databaseBuilder(this,RegistroDatabase::class.java,"Registro_db").build()

    }
}
