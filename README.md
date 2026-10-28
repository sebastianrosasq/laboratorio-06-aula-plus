# Laboratorio 6

Aplicación Android con Jetpack Compose y Material 3. Incluye navegación, perfil y un contador.

El ejemplo de la barra de la guía está en `EjemploGuia.kt`. Para abrirlo después de instalar la aplicación:

```powershell
adb shell am start -n pe.edu.aulaplus.lab06/.MainActivity --ez ejemplo_guia true
```

El ejercicio guiado con perfil, navegación y contador se abre con:

```powershell
adb shell am start -n pe.edu.aulaplus.lab06/.MainActivity --ez ejercicio_guiado true
```

Para compilar el proyecto:

```powershell
./gradlew.bat :app:assembleDebug
```

El diseño de las cuatro vistas de Aula+ en Figma está pendiente.
