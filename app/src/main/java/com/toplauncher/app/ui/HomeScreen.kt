package com.toplauncher.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.toplauncher.app.data.MinecraftHelper

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    var installed by remember { mutableStateOf(false) }
    var version by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        installed = MinecraftHelper.isInstalled(context)
        version = MinecraftHelper.installedVersion(context)
    }

    Scaffold(
        containerColor = NavyBackground,
        bottomBar = { BottomNavBar(navController, "home") }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 18.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(22.dp).background(GreenStatus, RoundedCornerShape(6.dp)))
                Spacer(Modifier.width(10.dp))
                Text("TopLauncher", color = TextMain, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Spacer(Modifier.height(16.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF2A1A4A), Color(0xFF6A3B7A), Color(0xFFE08A5B))
                        )
                    )
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Играй с модами, шейдерами и друзьями",
                        color = TextMain, fontWeight = FontWeight.Bold, fontSize = 16.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text("Библиотека контента для Minecraft PE", color = TextMuted, fontSize = 12.sp)
                }
            }

            Spacer(Modifier.height(20.dp))
            Text(
                if (installed) "Minecraft PE обнаружен \u00B7 $version" else "Minecraft PE не установлен",
                color = if (installed) GreenStatus else OrangeStatus,
                fontSize = 13.sp
            )
            Spacer(Modifier.height(10.dp))

            Button(
                onClick = { MinecraftHelper.launch(context) },
                colors = ButtonDefaults.buttonColors(containerColor = PurpleAccent),
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(if (installed) "\u25B6 ИГРАТЬ" else "Установить из Google Play", fontWeight = FontWeight.Bold)
            }
        }
    }
}