package com.example.curriculum.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    secondary = PrimaryBlue,
    tertiary = PrimaryBlue,
    background = Color(0xFF1C1C1E),
    surface = Color(0xFF1C1C1E),
    onPrimary = WhiteText,
    onSecondary = WhiteText,
    onTertiary = WhiteText,
    onBackground = WhiteText,
    onSurface = WhiteText,
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = PrimaryBlue,
    tertiary = PrimaryBlue,
    background = Color(0xFFF2F2F7),
    surface = Color(0xFFFFFFFF),
    onPrimary = WhiteText,
    onSecondary = WhiteText,
    onTertiary = WhiteText,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun CurriculumTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
