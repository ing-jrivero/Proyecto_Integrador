package com.example.proyecto_integrador.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RegistroEntity (@PrimaryKey val email: String = "",val proveedor: String = "") {
}