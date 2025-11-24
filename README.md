## 📱 TCG Table Organizer — Staff App

Aplicación móvil desarrollada en **Android Studio + Jetpack Compose** para la gestión interna de tiendas TCG (Trading Card Games), enfocada principalmente en eventos y organización de mesas en formatos casuales como **Commander**.

El objetivo es entregar a los dueños y trabajadores de las tiendas una herramienta rápida, visual y eficiente para administrar la ocupación de mesas, ver eventos y manejar la actividad interna de la tienda.

---

## ✨ Características principales

- 🔐 **Login exclusivo para administradores**
- 🏠 **Home administrativo** con accesos rápidos
- 🗓 **Vista de eventos** con información clara y ordenada
- 🧩 **Gestión visual de mesas (EMPTY / FULL)**
- 💬 Chat grupal modo administrador *(WIP)*
- 🎨 UI creada con **Material 3**, diseño limpio y moderno
- 🔄 Navegación completa con **Navigation Compose**
- ⚙️ Base estructurada para escalabilidad futura

---

## 📸 Capturas de pantalla

> Todas las imágenes deben ir en:  
> **`/screenshots/`** dentro del repositorio.

### 🔐 Login  
<img src="/screenshots/login.png" width="350"/>

### 🏠 Home  
<img src="/screenshots/home.png" width="350"/>

### 🗓 Vista de Eventos  
<img src="/screenshots/events.png" width="350"/>

### 🪑 Mapa de Mesas  
<img src="/screenshots/tables.png" width="350"/>

### 👤 Perfil (placeholder)  
<img src="/screenshots/profile.png" width="350"/>

---

## 🧩 Tecnologías utilizadas

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Compose Navigation**
- **State Management con remember / mutableStateOf**
- **JUnit + Instrumented Tests**
- Compatibilidad: desde **SDK 24** hasta **SDK 36**

---

## 📂 Arquitectura del proyecto

TCGTableOrganizerStaff/
│── app/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/com/cristobal/tcgtableorganizerstaff/
│ │ │ │ ├── MainActivity.kt
│ │ │ │ ├── ui/theme/
│ │ │ │ ├── navigation/
│ │ │ ├── res/
│ │ │ │ ├── drawable/
│ │ │ │ ├── values/
│ │ │ │ ├── xml/
│ │ ├── androidTest/
│ │ ├── test/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
## 🧩 **Tecnologías Utilizadas**

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **AndroidX**
- **Compose BOM**
- **JUnit / Instrumented Tests**
- **Gradle Kotlin DSL**

---

## 🚀 **Características Principales**

### 🔐 **1. Vista de Login**
- Validación básica (correo y contraseña)
- Opción “Mantener sesión iniciada”
- Mensajes de error claros
- Redirección segura al panel principal

### 🏠 **2. Vista Home (Staff Dashboard)**
- Evento destacado de la semana
- Acceso rápido a tiendas/promos
- Noticias y avisos Wizard

### 🪑 **3. Vista Mesas**
- Mapa visual de mesas
- Estado en tiempo real:
  - 🟢 **EMPTY**
  - 🔴 **FULL**
- Fichas con:
  - Nombre de mesa
  - Estado
  - Tres slots de jugador
- Futuro: añadir/eliminar mesas dinámicamente

### 💬 **4. Vista Chat (Lobby Admin)**
- Pendiente de implementación
- Será el espacio donde staff administrará comunicación entre mesas o jugadores

### 👤 **5. Vista Perfil**
- Placeholder listo para expansión futura

### 📅 **6. Eventos**
- Lista de eventos del local
- Filtros por tienda / eventos
- Tarjetas modernas con día, hora y formato

---

## 🗺 **Navegación**

Se utiliza **Navigation Compose** con dos niveles:

1. **AppNavHost**
   - Login
   - Main

2. **MainScreen**
   - Controlado con una BottomBar con 5 tabs:
     - Home  
     - Eventos  
     - Mesas  
     - Chat  
     - Perfil  

---

## 📸 **Screenshots**

https://github.com/StaRxScreaM/TCGTableOrganizerStaff/tree/master/screenshots

### 🧪 Testing

La app incluye:

✔ Instrumented Test

Ubicado en:

app/src/androidTest/java/com/cristobal/tcgtableorganizerstaff/ExampleInstrumentedTest.kt


Prueba el packageName.

✔ Unit Test

Ubicado en:

app/src/test/java/com/cristobal/tcgtableorganizerstaff/ExampleUnitTest.kt


Prueba funcionalidad básica.

### 🛠 Cómo Ejecutar el Proyecto

Clonar el repositorio:

git clone https://github.com/StaRxScreaM/TCGTableOrganizerStaff


Abrir en Android Studio

Permitir que Gradle sincronice

Ejecutar con un dispositivo o emulador Android

### 🗺 Roadmap (Futuro Desarrollo)

 Chat interno completo (texto e imágenes)

 Gestión dinámica de mesas (añadir/eliminar desde la app)

 Panel de estadísticas de uso

 Base de datos interna con Room

 Login real con Firebase / Supabase

### 📄 Licencia

Este proyecto se distribuye bajo licencia MIT.

👤 Autor

Cristóbal Tapia (StaRxScreaM)
Proyecto desarrollado para Bootcamp UNAB 2025.
