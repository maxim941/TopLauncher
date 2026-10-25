package com.toplauncher.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.toplauncher.app.data.CatalogData

@Composable
fun ResourcesScreen(navController: NavController) {
    Scaffold(
        containerColor = NavyBackground,
        bottomBar = { BottomNavBar(navController, "resources") }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(horizontal = 18.dp)) {
            Spacer(Modifier.height(16.dp))
            Text("Ресурсы", color = TextMain, fontSize = 20.sp)
            Spacer(Modifier.height(12.dp))

            CatalogData.items.filter { it.category == "resource" }.forEach { item ->
                AppCard(
                    emoji = item.emoji,
                    title = item.title,
                    subtitle = "${item.version} \u00B7 ${item.sizeText} \u00B7 \u2B50${item.rating}",
                    trailing = { Badge("\u203A", PurpleLight) },
                    onClick = { navController.navigate("mod_detail/${item.id}") }
                )
            }
        }
    }
}