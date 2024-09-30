package com.androidcourse.proyectfinal.ui.theme

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.androidcourse.proyectfinal.*


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjercicioScreen(database: EjercicioDatabase) {
    val ejercicioDao = database.ejercicioDao()
    val scope = rememberCoroutineScope()
    val ejercicios = remember { mutableStateListOf<Ejercicio>() }
    var nombre by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        ejercicios.addAll(ejercicioDao.getAll())
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Ejercicio CRUD") })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val nuevoEjercicio = Ejercicio(nombre = nombre)
                        scope.launch {
                            ejercicioDao.insert(nuevoEjercicio)
                            ejercicios.add(nuevoEjercicio)
                            nombre = ""
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Agregar Ejercicio")
                }

                Spacer(modifier = Modifier.height(24.dp))

                LazyColumn {
                    items(ejercicios) { ejercicio ->
                        Text("Nombre: ${ejercicio.nombre}")
                        Button(onClick = {
                            scope.launch {
                                ejercicioDao.delete(ejercicio)
                                ejercicios.remove(ejercicio)
                            }
                        }) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    )
}
