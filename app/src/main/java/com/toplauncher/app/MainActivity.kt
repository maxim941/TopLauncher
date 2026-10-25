package com.toplauncher.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.toplauncher.app.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopLauncherTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("mods") { ModsScreen(navController) }
                    composable("resources") { ResourcesScreen(navController) }
                    composable("profile") { ProfileScreen(navController) }
                    composable("settings") { SettingsScreen(navController) }
                    composable(
                        "mod_detail/{modId}",
                        arguments = listOf(navArgument("modId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val modId = backStackEntry.arguments?.getInt("modId") ?: 0
                        ModDetailScreen(navController, modId)
                    }
                }
            }
        }
    }
}