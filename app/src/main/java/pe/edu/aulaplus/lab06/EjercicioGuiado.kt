package pe.edu.aulaplus.lab06

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
    Opcion("Inicio", Icons.Default.Home),
    Opcion("Buscar", Icons.Default.Search),
    Opcion("Favoritos", Icons.Default.Favorite),
    Opcion("Ajustes", Icons.Default.Settings)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjercicioGuiado() {
    var seleccion by rememberSaveable { mutableIntStateOf(0) }
    var perfil by rememberSaveable { mutableStateOf(false) }
    var pulsaciones by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (perfil) "Perfil" else opciones[seleccion].nombre) },
                navigationIcon = {
                    if (perfil) IconButton(onClick = { perfil = false }) {
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
            if (!perfil && seleccion == 0) {
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
            Text(if (perfil) "Perfil de usuario" else opciones[seleccion].nombre, style = MaterialTheme.typography.headlineSmall)
            Text(if (perfil) "Información del usuario" else when (seleccion) {
                0 -> "Pulsaciones del botón: $pulsaciones"
                1 -> "Vista de búsqueda"
                2 -> "Vista de favoritos"
                else -> "Vista de ajustes"
            })
        }
    }
}
