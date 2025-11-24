package com.cristobal.tcgtableorganizerstaff

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.TableChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cristobal.tcgtableorganizerstaff.ui.theme.Burgundy
import com.cristobal.tcgtableorganizerstaff.ui.theme.SecondaryGray
import com.cristobal.tcgtableorganizerstaff.ui.theme.SurfaceGray
import com.cristobal.tcgtableorganizerstaff.ui.theme.TCGTableOrganizerStaffTheme
import com.cristobal.tcgtableorganizerstaff.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TCGTableOrganizerStaffTheme {
                AppNavHost()
            }
        }
    }
}

/* ==========================================================
                      NAVEGACIÓN RAÍZ
   ========================================================== */

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Main : Screen("main")      // Contiene bottom bar y tabs
}

@Composable
fun AppNavHost() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}

/* ==========================================================
                           LOGIN
   ========================================================== */

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {

    val context = LocalContext.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var keepLogged by rememberSaveable { mutableStateOf(false) }
    var error by rememberSaveable { mutableStateOf<String?>(null) }

    val adminEmail = "admin@tienda.cl"
    val adminPass = "admin123"

    fun login() {
        when {
            !email.contains("@") -> error = "Correo inválido"
            password.length < 4 -> error = "La contraseña es muy corta"
            email == adminEmail && password == adminPass -> {
                error = null
                Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                onLoginSuccess()
            }
            else -> error = "Credenciales incorrectas"
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_tcg_1),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .heightIn(max = 200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "TCG TABLE ORGANIZER",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Correo") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Contraseña") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(checked = keepLogged, onCheckedChange = { keepLogged = it })
                Text("Mantener sesión iniciada")
            }

            if (error != null) {
                Text(
                    error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { login() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar")
            }
        }
    }
}

/* ==========================================================
                        MAIN + BOTTOM BAR
   ========================================================== */

enum class MainTab(val label: String) {
    HOME("INICIO"),
    EVENTS("EVENTOS"),
    TABLES("MESAS"),
    CHAT("CHAT"),
    PROFILE("PERFIL")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by rememberSaveable { mutableStateOf(MainTab.HOME) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_tcg_1),
                            contentDescription = "Logo TCG Table Organizer",
                            modifier = Modifier
                                .height(32.dp)
                                .padding(end = 8.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: ir a perfil admin */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Perfil",
                            tint = White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: ir a ajustes */ }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Ajustes",
                            tint = White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Burgundy
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF181818),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = selectedTab == MainTab.HOME,
                    onClick = { selectedTab = MainTab.HOME },
                    icon = { Icon(Icons.Outlined.Home, contentDescription = "Inicio") },
                    label = { Text(MainTab.HOME.label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Burgundy,
                        selectedTextColor = Burgundy,
                        unselectedIconColor = SecondaryGray,
                        unselectedTextColor = SecondaryGray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == MainTab.EVENTS,
                    onClick = { selectedTab = MainTab.EVENTS },
                    icon = { Icon(Icons.Filled.Event, contentDescription = "Eventos") },
                    label = { Text(MainTab.EVENTS.label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Burgundy,
                        selectedTextColor = Burgundy,
                        unselectedIconColor = SecondaryGray,
                        unselectedTextColor = SecondaryGray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == MainTab.TABLES,
                    onClick = { selectedTab = MainTab.TABLES },
                    icon = { Icon(Icons.Outlined.TableChart, contentDescription = "Mesas") },
                    label = { Text(MainTab.TABLES.label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Burgundy,
                        selectedTextColor = Burgundy,
                        unselectedIconColor = SecondaryGray,
                        unselectedTextColor = SecondaryGray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == MainTab.CHAT,
                    onClick = { selectedTab = MainTab.CHAT },
                    icon = { Icon(Icons.Outlined.ChatBubbleOutline, contentDescription = "Chat") },
                    label = { Text(MainTab.CHAT.label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Burgundy,
                        selectedTextColor = Burgundy,
                        unselectedIconColor = SecondaryGray,
                        unselectedTextColor = SecondaryGray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = selectedTab == MainTab.PROFILE,
                    onClick = { selectedTab = MainTab.PROFILE },
                    icon = { Icon(Icons.Outlined.Person, contentDescription = "Perfil") },
                    label = { Text(MainTab.PROFILE.label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Burgundy,
                        selectedTextColor = Burgundy,
                        unselectedIconColor = SecondaryGray,
                        unselectedTextColor = SecondaryGray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = Color(0xFFF5F5F5))
        ) {
            when (selectedTab) {
                MainTab.HOME -> HomeTab()
                MainTab.EVENTS -> EventsTab()
                MainTab.TABLES -> TablesTab()
                MainTab.CHAT -> ChatTab()
                MainTab.PROFILE -> ProfileTab()
            }
        }
    }
}

/* ==========================================================
                           HOME
   ========================================================== */

@Composable
fun HomeTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "EVENTO DESTACADO DE LA SEMANA",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFF3B2424))
                    .padding(16.dp)
            ) {
                Text(
                    text = "COMMANDER NIGHT",
                    style = MaterialTheme.typography.titleLarge,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "ENE 15 - ÁREA 52",
                    style = MaterialTheme.typography.bodyMedium,
                    color = White
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = { /* TODO: acción ver más */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Burgundy)
                ) {
                    Text("VER MÁS")
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "TIENDAS DESTACADAS",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HomePillButton(
                text = "PROMOS",
                isActive = true,
                modifier = Modifier.weight(1f)
            )
            HomePillButton(
                text = "TIENDAS",
                isActive = false,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "ÚLTIMAS PROMOS O NOTICIAS WIZARD",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HomePillButton(
    text: String,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val bg = if (isActive) Burgundy else SurfaceGray
    val txt = if (isActive) White else Color(0xFF333333)

    Box(
        modifier = modifier
            .height(60.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(bg),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = txt, fontWeight = FontWeight.SemiBold)
    }
}

/* ==========================================================
                         EVENTOS
   ========================================================== */

data class EventUi(
    val dayShort: String,
    val dayNumber: String,
    val time: String,
    val title: String,
    val storeLabelTime: String
)

@Composable
fun EventsTab() {

    val events = remember {
        listOf(
            EventUi("ENE", "10", "10:00 a.m.", "CommanderCasual", "10:00 a.m."),
            EventUi("ENE", "10", "11:00 a.m.", "CommanderCasual", "11:00 a.m."),
            EventUi("ENE", "11", "1:00 p.m.", "CommanderCasual", "1:00 p.m.")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "EVENTOS",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "2026",
            style = MaterialTheme.typography.bodyMedium,
            color = SecondaryGray
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Burgundy),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Eventos",
                    color = White,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(SurfaceGray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tiendas",
                    color = SecondaryGray,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(events) { event ->
                EventCard(event)
            }
        }
    }
}

@Composable
fun EventCard(event: EventUi) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(event.dayShort, color = SecondaryGray)
                Text(
                    event.dayNumber,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Text(event.time, color = SecondaryGray)
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.SportsEsports,
                        contentDescription = null,
                        tint = Burgundy,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("TIENDA", color = SecondaryGray)
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = event.storeLabelTime,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/* ==========================================================
                          MESAS
   ========================================================== */

enum class TableStatus { EMPTY, FULL }

data class TableUi(
    val name: String,
    val status: TableStatus
)

@Composable
fun TablesTab() {

    val tables = remember {
        listOf(
            TableUi("MESA 1", TableStatus.EMPTY),
            TableUi("MESA 2", TableStatus.FULL),
            TableUi("MESA 3", TableStatus.EMPTY),
            TableUi("MESA 4", TableStatus.EMPTY),
            TableUi("MESA 5", TableStatus.FULL),
            TableUi("MESA 6", TableStatus.EMPTY)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        for (rowIndex in tables.indices step 2) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                MesaCard(
                    table = tables[rowIndex],
                    modifier = Modifier.weight(1f)
                )

                if (rowIndex + 1 < tables.size) {
                    MesaCard(
                        table = tables[rowIndex + 1],
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(modifier = Modifier.width(0.dp))
                }
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
fun MesaCard(table: TableUi, modifier: Modifier = Modifier) {

    val (bgChip, txtChip) = when (table.status) {
        TableStatus.EMPTY -> SurfaceGray to Color.DarkGray
        TableStatus.FULL -> Burgundy to White
    }

    Card(
        modifier = modifier.height(140.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = table.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(bgChip)
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = table.status.name,
                    color = txtChip,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF3A3A3A))
                    )
                }
            }
        }
    }
}

/* ==========================================================
                     CHAT & PERFIL (PLACEHOLDERS)
   ========================================================== */

@Composable
fun ChatTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Chat en desarrollo", color = SecondaryGray)
    }
}

@Composable
fun ProfileTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Perfil en desarrollo", color = SecondaryGray)
    }
}
