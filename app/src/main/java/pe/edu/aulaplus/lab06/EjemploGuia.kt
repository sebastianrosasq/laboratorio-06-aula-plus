package pe.edu.aulaplus.lab06

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/** Primera etapa del ejercicio: iconos de la guía distribuidos uniformemente. */
@Composable
fun EjemploGuia() {
    Scaffold(bottomBar = {
        BottomAppBar {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                IconButton(onClick = { print("Build") }) {
                    Icon(Icons.Filled.Build, contentDescription = "Build description")
                }
                IconButton(onClick = { print("Menu") }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu description")
                }
                IconButton(onClick = { print("Favorite") }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite description")
                }
                IconButton(onClick = { print("Delete") }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete description")
                }
            }
        }
    }) { padding ->
        Text("Ejemplo de la guía: barra inferior", modifier = Modifier.padding(padding))
    }
}
