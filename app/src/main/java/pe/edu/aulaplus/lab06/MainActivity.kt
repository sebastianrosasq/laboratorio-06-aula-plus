package pe.edu.aulaplus.lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val ejemploGuia = intent.getBooleanExtra("ejemplo_guia", false)
        setContent { AulaTheme { if (ejemploGuia) EjemploGuia() else AulaApp() } }
    }
}

private val AulaColors = lightColorScheme(
    primary = Color(0xFF365F9C), onPrimary = Color.White,
    primaryContainer = Color(0xFFD7E3FF), onPrimaryContainer = Color(0xFF001B3E),
    secondary = Color(0xFF526079), secondaryContainer = Color(0xFFD9E3F9),
    background = Color(0xFFF9F9FF), surface = Color(0xFFF9F9FF)
)

private val AulaShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp)
)

// Compose uses Roboto on Android, with the Material 3 type scale.
private val AulaTypography = Typography()

@Composable
private fun AulaTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = AulaColors, typography = AulaTypography, shapes = AulaShapes, content = content)
}

private data class Destination(val title: String, val icon: ImageVector)
private val destinations = listOf(
    Destination("Inicio", Icons.Default.Home),
    Destination("Cursos", Icons.Default.Book),
    Destination("Asistencia", Icons.Default.CheckCircle),
    Destination("Mensajes", Icons.Default.Email)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AulaApp() {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    var profile by rememberSaveable { mutableStateOf(false) }
    var count by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (profile) "Perfil" else "Aula+ · ${destinations[selected].title}") },
                navigationIcon = {
                    if (profile) IconButton(onClick = { profile = false }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                actions = {
                    IconButton(onClick = { profile = true }) {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = "Abrir perfil")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                destinations.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = !profile && selected == index,
                        onClick = { selected = index; profile = false },
                        icon = { Icon(destination.icon, contentDescription = null) },
                        label = { Text(destination.title) }
                    )
                }
            }
        },
        floatingActionButton = {
            if (!profile && selected == 0) FloatingActionButton(onClick = { count++ }) {
                Icon(Icons.Default.Add, contentDescription = "Incrementar contador")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (profile) {
                item { Header("Perfil docente", "Datos de demostración para la interfaz del laboratorio.") }
                item { InfoCard("Docente", "Cuenta de demostración", "Aula+ · Perfil") }
            } else when (selected) {
                0 -> {
                    item { Header("Hola, docente", "Organiza tu jornada desde un solo lugar.") }
                    item { InfoCard("Acciones rápidas", "Pulsaciones del botón: $count", "El botón flotante actualiza este contador.") }
                    item { InfoCard("Tu jornada", "Cursos, asistencia y mensajes", "Selecciona una opción de la barra inferior.") }
                }
                1 -> {
                    item { Header("Cursos", "Consulta las aulas y sus actividades.") }
                    item { InfoCard("Mis cursos", "Espacio de cursos", "Aquí se mostrarán las clases asignadas.") }
                }
                2 -> {
                    item { Header("Asistencia", "Registra la asistencia de tus estudiantes.") }
                    item { InfoCard("Registro de asistencia", "Estudiante · Aula · Fecha · Estado", "El laboratorio 7 implementa la persistencia local con Room.") }
                }
                3 -> {
                    item { Header("Mensajes", "Mantén el contacto con las familias.") }
                    item { InfoCard("Bandeja", "Comunicaciones del aula", "Aquí se mostrarán los mensajes del curso.") }
                }
            }
        }
    }
}

@Composable
private fun Header(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
    }
}

@Composable
private fun InfoCard(title: String, main: String, detail: String) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
        Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text(main, style = MaterialTheme.typography.bodyLarge)
            Text(detail, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
