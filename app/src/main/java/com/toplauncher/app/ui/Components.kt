package com.toplauncher.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AppCard(
    emoji: String,
    title: String,
    subtitle: String,
    trailing: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(NavyCard, RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(PurpleAccent.copy(alpha = 0.25f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) { Text(emoji, fontSize = 22.sp) }

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(title, color = TextMain, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text(subtitle, color = TextMuted, fontSize = 12.sp)
        }
        trailing()
    }
    Spacer(Modifier.height(10.dp))
}

@Composable
fun Badge(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.18f), RoundedCornerShape(20.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) { Text(text, color = color, fontSize = 11.sp, fontWeight = FontWeight.Bold) }
}

@Composable
fun BottomNavBar(navController: NavController, current: String) {
    val items = listOf(
        Triple("home", "\uD83C\uDFE0", "Главная"),
        Triple("mods", "\uD83E\uDDE9", "Моды"),
        Triple("resources", "\uD83D\uDCE6", "Ресурсы"),
        Triple("profile", "\uD83D\uDC64", "Профиль")
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(NavySurface)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { (route, icon, label) ->
            val active = current == route
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navController.navigate(route) {
                        popUpTo("home"); launchSingleTop = true
                    }
                }
            ) {
                Text(icon, fontSize = 18.sp)
                Text(
                    label, fontSize = 10.sp,
                    color = if (active) PurpleLight else TextFaded
                )
            }
        }
    }
}

@Composable
fun BackRow(navController: NavController) {
    Text(
        "\u2190 Назад",
        color = PurpleLight,
        modifier = Modifier.clickable { navController.popBackStack() }
    )
}