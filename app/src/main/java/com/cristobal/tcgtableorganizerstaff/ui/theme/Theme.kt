package com.cristobal.tcgtableorganizerstaff.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = Burgundy,
    onPrimary = White,
    secondary = Carbon,
    onSecondary = White,
    background = SurfaceGray,
    onBackground = Carbon,
    surface = White,
    onSurface = Carbon
)

private val DarkColors = darkColorScheme(
    primary = Burgundy,
    onPrimary = White,
    secondary = SurfaceGray,
    onSecondary = Carbon,
    background = Carbon,
    onBackground = SurfaceGray,
    surface = Carbon,
    onSurface = SurfaceGray
)

@Composable
fun TCGTableOrganizerStaffTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    val view = LocalView.current

    if (!view.isInEditMode) {
        @Suppress("DEPRECATION") // Eliminamos el warning sin romper compatibilidad
        SideEffect {
            val window = (view.context as Activity).window

            // Control moderno de insets
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
            }

            // Color de la status bar (estilo burgundy)
            window.statusBarColor = colorScheme.primary.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
