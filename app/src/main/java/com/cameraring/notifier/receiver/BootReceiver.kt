package com.cameraring.notifier.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.cameraring.notifier.service.CameraRingOverlayService

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        if (
            intent.action ==
            Intent.ACTION_BOOT_COMPLETED
        ) {

            val serviceIntent =
                Intent(
                    context,
                    CameraRingOverlayService::class.java
                )

            context.startForegroundService(
                serviceIntent
            )
        }
    }
}