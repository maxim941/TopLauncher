package com.toplauncher.app.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NavyBackground = Color(0xFF0A0E24)
val NavySurface = Color(0xFF151A36)
val NavyCard = Color(0xFF181D3D)
val PurpleAccent = Color(0xFF7C5CFC)
val PurpleLight = Color(0xFF9B7BFF)
val GreenStatus = Color(0xFF4ADE80)
val OrangeStatus = Color(0xFFFB923C)
val TextMain = Color(0xFFF1F2FA)
val TextMuted = Color(0xFF9195BA)
val TextFaded = Color(0xFF5B6088)

@Composable
fun TopLauncherTheme(content: @Composable () -> Unit) {
    val colors = darkColorScheme(
        primary = PurpleAccent,
        secondary = PurpleLight,
        background = NavyBackground,
        surface = NavySurface,
        onPrimary = Color.White,
        onBackground = TextMain,
        onSurface = TextMain
    )
    MaterialTheme(colorScheme = colors, content = content)
}