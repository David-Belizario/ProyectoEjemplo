package com.androidcourse.proyectfinal

import androidx.room.*

@Dao
interface EjercicioDao {
    @Query("SELECT * FROM ejercicios")
    suspend fun getAll(): List<Ejercicio>

    @Insert
    suspend fun insert(ejercicio: Ejercicio)

    @Delete
    suspend fun delete(ejercicio: Ejercicio)
}
