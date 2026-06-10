package com.cameraring.notifier

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.NotificationManagerCompat
import com.cameraring.notifier.ui.settings.SettingsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        requestOverlayPermission()

        requestNotificationListenerPermission()

        setContent {
            SettingsScreen()
        }
    }

    private fun requestOverlayPermission() {

        if (!Settings.canDrawOverlays(this)) {

            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )

            startActivity(intent)
        }
    }

    private fun requestNotificationListenerPermission() {

        val enabledListeners =
            NotificationManagerCompat
                .getEnabledListenerPackages(this)

        if (!enabledListeners.contains(packageName)) {

            startActivity(
                Intent(
                    Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
                )
            )
        }
    }
}