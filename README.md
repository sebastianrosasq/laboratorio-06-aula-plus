# Aula+ Laboratorio 6

Aplicación de práctica en Kotlin y Jetpack Compose. La barra inferior abre las vistas de inicio, cursos, asistencia y mensajes. El icono superior abre el perfil y el botón flotante incrementa un contador en la pantalla principal. Las vistas usan contenido de demostración y no se conectan al sistema web de Aula+.

El tema utiliza Material 3 con color primario `#365F9C`, contenedor `#D7E3FF`, tipografía Roboto y formas redondeadas. El diseño relacionado está en [Figma](https://www.figma.com/design/YV6YfVh0dEvtFEPOFWkS06).

Para compilar desde la raíz del proyecto ejecuta `./gradlew.bat :app:assembleDebug`. El APK de depuración aparece en `app/build/outputs/apk/debug/app-debug.apk`.

El ejemplo inicial de la guía conserva los iconos Build, Menu, Favorite y Delete, distribuidos en la barra. Tras instalar la aplicación se puede abrir con `adb shell am start -n pe.edu.aulaplus.lab06/.MainActivity --ez ejemplo_guia true`. Al iniciar la aplicación normalmente se muestra la versión personalizada de Aula+.

La [demostración en video](evidencias/lab06-demostracion.mp4) muestra el funcionamiento de Aula+.

El proyecto de usuarios y asistencia con Room se encuentra en [el repositorio del laboratorio 7](https://github.com/sebastianrosasq/laboratorio-07-aula-plus).
