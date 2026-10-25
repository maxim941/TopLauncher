package com.toplauncher.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.toplauncher.app.data.CatalogData
import com.toplauncher.app.data.FileImporter
import kotlinx.coroutines.delay

@Composable
fun ModDetailScreen(navController: NavController, modId: Int) {
    val context = LocalContext.current
    val item = CatalogData.items.find { it.id == modId } ?: return

    var url by remember { mutableStateOf("") }
    var downloadId by remember { mutableStateOf<Long?>(null) }
    var progress by remember { mutableStateOf(0) }
    var isDone by remember { mutableStateOf(false) }
    var localUri by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(downloadId) {
        while (downloadId != null && !isDone) {
            val status = FileImporter.queryProgress(context, downloadId!!)
            progress = status.progress
            isDone = status.isDone
            localUri = status.localUri
            delay(800)
        }
    }

    Scaffold(containerColor = NavyBackground) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
            BackRow(navController)
            Spacer(Modifier.height(16.dp))
            Text(item.emoji, fontSize = 40.sp)
            Text(item.title, color = TextMain, fontSize = 18.sp)
            Text("${item.version} \u00B7 ${item.sizeText} \u00B7 \u2B50${item.rating}", color = TextMuted, fontSize = 12.sp)
            Spacer(Modifier.height(12.dp))
            Text(item.description, color = TextMuted, fontSize = 13.sp)

            Spacer(Modifier.height(20.dp))
            Text(
                "У нас пока нет своего сервера с файлами, вставь реальную прямую ссылку на .mcpack/.mcaddon для теста загрузки:",
                color = TextFaded, fontSize = 11.sp
            )

            OutlinedTextField(
                value = url, onValueChange = { url = it },
                placeholder = { Text("https://.../file.mcaddon") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            if (downloadId == null) {
                Button(
                    onClick = {
                        if (url.isNotBlank()) {
                            val fileName = url.substringAfterLast("/")
                            downloadId = FileImporter.startDownload(context, url, fileName)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PurpleAccent),
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Скачать") }
            } else {
                LinearProgressIndicator(progress = progress / 100f, modifier = Modifier.fillMaxWidth())
                Text("$progress%", color = TextMuted, fontSize = 12.sp)

                Spacer(Modifier.height(12.dp))

                if (isDone && localUri != null) {
                    Button(
                        onClick = { FileImporter.importToMinecraft(context, localUri!!) },
                        colors = ButtonDefaults.buttonColors(containerColor = GreenStatus),
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Импортировать в Minecraft") }
                }
            }
        }
    }
}