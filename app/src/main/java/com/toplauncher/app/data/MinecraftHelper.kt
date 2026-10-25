package com.toplauncher.app.data

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

const val MINECRAFT_PACKAGE = "com.mojang.minecraftpe"

object MinecraftHelper {

    fun isInstalled(context: Context): Boolean = try {
        context.packageManager.getPackageInfo(MINECRAFT_PACKAGE, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }

    fun installedVersion(context: Context): String? = try {
        context.packageManager.getPackageInfo(MINECRAFT_PACKAGE, 0).versionName
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }

    fun launch(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage(MINECRAFT_PACKAGE)
        if (intent != null) {
            context.startActivity(intent)
        } else {
            openInPlayStore(context)
        }
    }

    private fun openInPlayStore(context: Context) {
        try {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$MINECRAFT_PACKAGE"))
            )
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$MINECRAFT_PACKAGE"))
            )
        }
    }
}