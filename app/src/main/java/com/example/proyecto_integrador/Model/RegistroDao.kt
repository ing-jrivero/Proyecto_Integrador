package com.example.proyecto_integrador.Model

import androidx.room.*

@Dao
interface RegistroDao {

    @Query("SELECT * From RegistroEntity")
     fun getAllRegistros(): List<RegistroEntity>

  //  @Query("SELECT * From RegistroEntity WHERE email like :emailr")
  //  suspend fun getRegitroById(emailr: String):RegistroEntity

    @Insert
    fun addRegistro(registro: RegistroEntity): Long

    @Update
    fun updateRegistro(registro: RegistroEntity): Int

    @Delete
    fun deleteRegistro(registro: RegistroEntity): Int

    @Query("DELETE FROM RegistroEntity")
    fun deleteAllRegistros()

    @Query("SELECT COUNT(*) FROM RegistroEntity")
    fun CountRows(): Int
}