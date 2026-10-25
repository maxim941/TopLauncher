package com.toplauncher.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(containerColor = NavyBackground) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
            BackRow(navController)
            Spacer(Modifier.height(16.dp))
            Text("Настройки", color = TextMain, fontSize = 20.sp)
            Spacer(Modifier.height(16.dp))
            Text(
                "Хранение настроек (язык/тема) будет добавлено через DataStore \u2014 сейчас это заглушка интерфейса.",
                color = TextFaded, fontSize = 12.sp
            )
        }
    }
}