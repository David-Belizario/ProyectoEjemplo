package com.androidcourse.proyectfinal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ejercicios")
data class Ejercicio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String
)
