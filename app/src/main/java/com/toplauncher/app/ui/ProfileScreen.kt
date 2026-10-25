package com.toplauncher.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        containerColor = NavyBackground,
        bottomBar = { BottomNavBar(navController, "profile") }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
            Text("Профиль", color = TextMain, fontSize = 20.sp)
            Spacer(Modifier.height(16.dp))
            AppCard("\u2699\uFE0F", "Настройки", "Язык, тема, хранилище", { Badge("\u203A", PurpleLight) }) {
                navController.navigate("settings")
            }
            AppCard("\u2139\uFE0F", "О приложении", "TopLauncher v1.0.0", { Badge("\u203A", PurpleLight) }) {}
        }
    }
}