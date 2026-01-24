# Huella de Carbono - Android App 🌍🌱

Una aplicación móvil moderna construida con **Jetpack Compose** que permite a los usuarios calcular su huella de carbono personal basándose en la metodología y algoritmos de **Greenpeace**. El proyecto utiliza una arquitectura modular y navegación anidada para ofrecer una experiencia de usuario fluida y educativa.

## 🎓 Proyecto de Certificación
Este proyecto fue desarrollado como parte integral de la certificación **Android Software Developer** impartida por el **Tecnológico de Monterrey**. La aplicación demuestra el dominio de competencias clave en el desarrollo de software móvil, incluyendo:
* Gestión del **Ciclo de vida** una aplicación Android.
* Navegación avanzada con **NavHost** anidados.

## 🚀 Características

- **Pantalla de Inicio Educativa:** Información detallada sobre el impacto ambiental, metas globales de la ONU y estadísticas comparativas actualizadas al 2024.
- **Detalle por Pilares:** Secciones interactivas para profundizar en los tres pilares del cambio climático: **Transporte, Energía y Consumo**.
- **Calculadora de 10 Pasos:** Flujo de trabajo secuencial que evalúa:
    1.  **Hogar:** Electrodomésticos, consumo de gas y tipo de iluminación.
    2.  **Transporte:** Modos de transporte, tiempo de traslado y frecuencia de vuelos.
    3.  **Consumo:** Alimentación (carnes), uso de plásticos, consumo de ropa y renovación de dispositivos.
- **Indicador de Progreso Animado:** Seguimiento en tiempo real mediante un `LinearProgressIndicator` sincronizado dinámicamente con la navegación.
- **Resultados e Interpretación:** Cálculo total en $CO_{2}e$ con equivalencias en árboles ganados o talados y clasificación de impacto según el termómetro de Greenpeace.

## 🛠️ Stack Tecnológico

La aplicación utiliza las tecnologías de vanguardia recomendadas para el desarrollo moderno en Android:

- **Lenguaje:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/compose) (Material Design 3)
- **Navegación:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) (Implementación de NavHosts anidados)
- **Arquitectura:** MVVM (Model-View-ViewModel)
- **Mínimo SDK:** 24 (Android 7.0+)
- **Target SDK:** 36 (Android 15+)

## ⚙️ Instalación
1. Clona este repositorio.
2. Abre el proyecto en **Android Studio Ladybug** (o versión superior).
3. Asegúrate de tener configurado el **JDK 11** en los ajustes de compilación de Gradle.
4. Sincroniza el proyecto con los archivos Gradle.
5. Ejecuta la aplicación en un emulador o dispositivo físico con **API 24** o superior.

## 🎬 Videos de Demostración

A continuación, se presentan las evidencias del funcionamiento de la aplicación, destacando los flujos de navegación y la interacción con componentes externos:

### 1. Navegación Global mediante Bottom Navigation
Muestra la transición fluida entre los módulos principales de la aplicación utilizando la barra de navegación inferior.

https://github.com/user-attachments/assets/cbc934a3-7bee-4789-9fc7-8d9fcdb57d7e

### 2. Navegación con Envío de Parámetros
Demostración del paso de argumentos entre el menú principal y la pantalla de detalles, permitiendo visualizar información dinámica según el pilar seleccionado.

https://github.com/user-attachments/assets/54d7d702-173c-4b23-9639-62628ecf555c

### 3. Integración con Navegador Web Externo
Uso de **Intents** explícitos para abrir páginas de referencia y fuentes oficiales en el navegador del dispositivo, enriqueciendo el contenido educativo de la app. **Nota:** En el video no se observa la carga de pagina por ser un emulador que no tiene configurado la cuenta de Google

https://github.com/user-attachments/assets/876d2e32-b717-4cbe-82fe-383a2564b1ac

### 4. Navegación Interna de la Calculadora
Muestra el flujo secuencial del `InnerNavHost`, la validación de estados en el `ViewModel` y la actualización en tiempo real del `LinearProgressIndicator`.

https://github.com/user-attachments/assets/5bf0d413-7d05-41a8-8eb0-a0858edeb988

## 📄 Créditos y Lógica de Cálculo
Esta aplicación ha sido desarrollada con fines educativos. La lógica de cálculo, los coeficientes de emisión de $CO_{2}e$, las categorías de segmentación y los mensajes de interpretación han sido adaptados mediante ingeniería inversa de la calculadora oficial de **Greenpeace**.

> **Nota:** Todos los derechos sobre la metodología original y marcas asociadas pertenecen a Greenpeace. El desarrollo técnico de esta versión en Android es responsabilidad del autor.

---
**Desarrollado por:** `Alejandro Salazar Zavala`
