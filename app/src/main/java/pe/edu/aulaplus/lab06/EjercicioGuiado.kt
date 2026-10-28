package pe.edu.aulaplus.lab06

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

private data class Opcion(val nombre: String, val icono: ImageVector)
private val opciones = listOf(
    Opcion("Herramientas", Icons.Default.Build),
    Opcion("Opciones", Icons.Default.Menu),
    Opcion("Favoritos", Icons.Default.Favorite),
    Opcion("Eliminar", Icons.Default.Delete)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjercicioGuiado() {
    var seleccion by rememberSaveable { mutableIntStateOf(-1) }
    var perfil by rememberSaveable { mutableStateOf(false) }
    var pulsaciones by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (perfil) "Perfil" else opciones.getOrNull(seleccion)?.nombre ?: "Inicio") },
                navigationIcon = {
                    if (perfil || seleccion >= 0) IconButton(onClick = { perfil = false; seleccion = -1 }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { perfil = true }) {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = "Abrir perfil")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                opciones.forEachIndexed { indice, opcion ->
                    NavigationBarItem(
                        selected = !perfil && seleccion == indice,
                        onClick = { seleccion = indice; perfil = false },
                        icon = { Icon(opcion.icono, contentDescription = null) },
                        label = { Text(opcion.nombre) }
                    )
                }
            }
        },
        floatingActionButton = {
            if (!perfil && seleccion == -1) {
                FloatingActionButton(onClick = { pulsaciones++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Contar pulsación")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(if (perfil) "Perfil de usuario" else opciones.getOrNull(seleccion)?.nombre ?: "Inicio", style = MaterialTheme.typography.headlineSmall)
            Text(if (perfil) "Información del usuario" else when (seleccion) {
                -1 -> "Pulsaciones del botón: $pulsaciones"
                0 -> "Vista de herramientas"
                1 -> "Vista de opciones"
                2 -> "Vista de favoritos"
                else -> "Vista para eliminar"
            })
        }
    }
}
