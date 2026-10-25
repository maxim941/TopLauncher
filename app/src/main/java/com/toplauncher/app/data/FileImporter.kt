package com.toplauncher.app.data

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object FileImporter {

    fun startDownload(context: Context, url: String, fileName: String): Long {
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(fileName)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalFilesDir(context, "downloads", fileName)
            .setAllowedOverMetered(true)
        return manager.enqueue(request)
    }

    fun queryProgress(context: Context, downloadId: Long): DownloadStatus {
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val query = DownloadManager.Query().setFilterById(downloadId)
        val cursor: Cursor = manager.query(query)
        cursor.use {
            if (it.moveToFirst()) {
                val statusIdx = it.getColumnIndex(DownloadManager.COLUMN_STATUS)
                val totalIdx = it.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
                val downIdx = it.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                val localUriIdx = it.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)

                val status = it.getInt(statusIdx)
                val total = it.getLong(totalIdx)
                val downloaded = it.getLong(downIdx)
                val localUri = if (localUriIdx >= 0) it.getString(localUriIdx) else null

                val progress = if (total > 0) ((downloaded * 100) / total).toInt() else 0
                val isDone = status == DownloadManager.STATUS_SUCCESSFUL
                val isFailed = status == DownloadManager.STATUS_FAILED

                return DownloadStatus(progress, isDone, isFailed, localUri)
            }
        }
        return DownloadStatus(0, false, true, null)
    }

    fun importToMinecraft(context: Context, localFileUri: String): Boolean {
        val file = File(Uri.parse(localFileUri).path ?: return false)
        if (!file.exists()) return false

        val authority = "${context.packageName}.fileprovider"
        val contentUri = FileProvider.getUriForFile(context, authority, file)

        val mime = when (file.extension.lowercase()) {
            "mcpack" -> "application/mcpack"
            "mcaddon" -> "application/mcaddon"
            "mcworld" -> "application/mcworld"
            "mctemplate" -> "application/mctemplate"
            else -> "application/octet-stream"
        }

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(contentUri, mime)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        return try {
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            false
        }
    }
}

data class DownloadStatus(
    val progress: Int,
    val isDone: Boolean,
    val isFailed: Boolean,
    val localUri: String?
)