# TCG TABLE ORGANIZER – Versión Tiendas & Staff

TCG TABLE ORGANIZER es una aplicación móvil pensada para el ecosistema de juegos de cartas coleccionables (TCG), con foco en **Magic: The Gathering – Formato Commander Casual** y adaptable a otros juegos como Pokémon, Yu-Gi-Oh!, Flesh & Blood o Lorcana.

Esta versión de la app está orientada a **tiendas y encargados de eventos**, permitiendo administrar mesas de juego, organizar el espacio y moderar la experiencia social durante una jornada de juego.

---

## 🎯 Problemática que resuelve

Las tiendas que organizan eventos de TCG suelen enfrentarse a:

- Desorden en la distribución de mesas.
- Dificultad para saber cuántas mesas están ocupadas o disponibles.
- Falta de comunicación clara con los jugadores durante un evento.
- Uso de soluciones improvisadas (papel, pizarras, mensajes orales).

Esto puede generar:

- Pérdida de tiempo en la organización.
- Experiencia poco fluida para jugadores.
- Menor aprovechamiento del espacio físico.

---

## ✅ Solución propuesta

**TCG TABLE ORGANIZER – Versión Tiendas & Staff** ofrece un panel sencillo para el personal de la tienda, con:

1. **Login Staff Seguro**  
   Acceso exclusivo para administradores o encargados de tienda.

2. **Home Administrativo**  
   Pantalla principal con acceso rápido a:
   - **Organizar Mesas**  
     Gestionar la cantidad de mesas y su estado.
   - **Lobby / Chat Admin** (prototipo)  
     Pensado para moderar mensajes, fijar anuncios y enviar avisos globales.

3. **Mapa de Mesas (Vista Top-Down)**  
   Representación visual del espacio de la tienda:
   - Cada mesa se muestra como un bloque con:
     - Número de mesa.
     - Estado (EMPTY, IN GAME, FULL).
     - Cantidad de jugadores.
   - Permite agregar o reiniciar mesas de forma rápida.

La app está diseñada para ser:

- **Útil** durante eventos de Commander u otros formatos.
- **Flexible** para distintos juegos de cartas.
- **Rápida de usar**, incluso en momentos de alta carga de trabajo.

---

## 🧱 Arquitectura de pantallas

### 1. Vista Login (Staff)

- Campo de correo/usuario.
- Campo de contraseña.
- Opción “Mantener sesión iniciada”.
- Botón “Ingresar”.
- Mensajes de error claros en caso de credenciales incorrectas o formato inválido.

En esta versión se utiliza una **validación mock** con credenciales fijas para simular la autenticación, dejando preparado el flujo para una futura integración con API real.

---

### 2. Home Administrativo (Panel Principal)

Pantalla principal para el staff, con:

- Header superior en tono **burgundy** con:
  - Icono de perfil.
  - Logo mini de la app.
  - Icono de ajustes (reservado para futuras configuraciones).
- Dos tarjetas principales:
  - **Organizar Mesas**  
    Acceso al mapa de mesas.
  - **Lobby / Chat Admin** (en desarrollo)  
    Se presenta como módulo futuro para moderación de chat y anuncios.

---

### 3. Vista Mapa de Mesas

La vista clave para la tienda:

- Muestra las mesas como tarjetas en un listado o grid.
- Cada mesa incluye:
  - Nombre/ID: “Mesa 1”, “Mesa 2”, etc.
  - Estado:
    - `EMPTY` (disponible)
    - `IN_GAME` (en juego)
    - `FULL` (ocupación máxima)
  - Jugadores actuales vs. máximo.

Acciones disponibles:

- **Agregar Mesa**: crea una nueva mesa en el mapa.
- **Reiniciar Mesas**: limpia el mapa (todas las mesas se eliminan).
- Tap sobre una mesa:
  - Alterna su estado para simular el flujo real del evento.

En esta versión, los datos se manejan con una **fuente de datos simulada** (`FakeTableRepository`) para centrarse en la lógica de interacción y el diseño de la interfaz.

---

## 🛠️ Tecnologías utilizadas

- **Lenguaje:** Kotlin
- **Entorno:** Android Studio
- **Arquitectura básica:** separación por capas simples:
  - `ui` (pantallas y adaptadores)
  - `model` (modelos de datos)
  - `data` (fuente de datos mock)
- **Componentes de Android:**
  - Activities
  - RecyclerView / GridLayout (para el mapa de mesas)
  - Material Components para campos de texto y botones

Esta organización busca mantener el código **modular y mantenible**, alineado con las buenas prácticas vistas en el curso.

---

## 🧪 Pruebas realizadas

En esta etapa se consideraron principalmente **pruebas manuales**, enfocadas en la experiencia del usuario:

- Inicio de sesión con credenciales correctas e incorrectas.
- Validación de formato de correo.
- Navegación:
  - Login → Home Staff
  - Home Staff → Mapa de Mesas
- Gestión de mesas:
  - Agregar nuevas mesas.
  - Cambiar estado de mesas (EMPTY → IN_GAME → FULL).
  - Reiniciar listado de mesas.
- Comportamiento en dispositivos de distintas resoluciones (emulador).

Estas pruebas permiten asegurar que el flujo principal del staff sea funcional y estable para su uso en eventos reales.

---

## 🎨 Diseño y lineamientos visuales

La app mantiene una identidad visual consistente:

- **Colores principales:**
  - Burgundy: `#6B0B1D` (color predominante en headers y botones principales).
  - Carbon / Texto principal: `#1E1E1E`.
  - Surface Gray: `#EDEDED`.
  - Gray secundario: `#8A8A8A`.
  - Blanco: `#FFFFFF`.

- **Estilo:**
  - Flat UI, sin sombras exageradas.
  - Bordes redondeados limpios.
  - Iconografía tipo outlined para mantener claridad.

- **Tipografía:**
  - Negrita para títulos y headers.
  - Semi-bold para secciones importantes.
  - Regular para textos descriptivos.

Este diseño busca transmitir una sensación de **profesionalismo, orden y claridad**, alineada con el contexto de una tienda especializada en TCG.

---

## 🚀 Futuras mejoras

La arquitectura del proyecto está pensada para permitir futuras extensiones, como por ejemplo:

- Sistema de registro de jugadores por QR.
- Estadísticas de uso de mesas (horas pico, cantidad de mesas activas).
- Reservas de mesa por parte de jugadores desde otra versión de la app.
- Módulo completo de chat y lobby moderado en tiempo real.
- Múltiples roles (dueño de tienda, staff, juez).

---

## 💼 ¿Qué hace efectivo este portafolio?

Este proyecto forma parte de mi portafolio como desarrollador/a de aplicaciones Android y refleja varias características clave de un portafolio digital efectivo:

- **Coherencia visual:**  
  El diseño mantiene una paleta de colores, tipografías y estilo consistentes, reforzando una identidad clara del producto.

- **Presentación clara del proyecto:**  
  Se describe la problemática real de las tiendas TCG, la solución propuesta y las funcionalidades implementadas de forma concreta, lo que permite a un reclutador entender rápidamente el alcance del proyecto.

- **Tecnologías y buenas prácticas:**  
  Se explicitan las tecnologías utilizadas (Kotlin, Android Studio, arquitectura por capas) y se organiza el código de forma modular, demostrando conocimientos aplicables a proyectos reales.

- **Impacto en el perfil laboral:**  
  Este proyecto muestra mi capacidad para:
  - Diseñar una solución a un problema específico de un nicho real (comunidades TCG).
  - Implementar interfaces usables y consistentes.
  - Pensar en escalabilidad futura (módulos adicionales, integración con APIs).
  
En conjunto, este repositorio busca ser una pieza de portafolio que ayude a potenciales empleadores o clientes a evaluar mi forma de pensar, diseñar e implementar aplicaciones móviles.

---

## 📦 Cómo ejecutar el proyecto

1. Clonar este repositorio:

   ```bash
   git clone https://github.com/StaRxScreaM/TCGTableOrganizerStaff
