package com.androidcourse.proyectfinal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ejercicio::class], version = 1)
abstract class EjercicioDatabase : RoomDatabase() {
    abstract fun ejercicioDao(): EjercicioDao

    companion object {
        @Volatile
        private var INSTANCE: EjercicioDatabase? = null

        fun getDatabase(context: Context): EjercicioDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EjercicioDatabase::class.java,
                    "ejercicio_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
