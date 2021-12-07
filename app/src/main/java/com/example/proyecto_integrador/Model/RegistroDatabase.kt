package com.example.proyecto_integrador.Model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RegistroEntity::class),version = 1)
abstract class RegistroDatabase(): RoomDatabase() {

    abstract fun registroDao(): RegistroDao
}