package com.cameraring.notifier.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.os.Handler
import android.os.Looper

class CameraRingOverlayService : Service() {

    private lateinit var windowManager: WindowManager

    private var overlayView: RingOverlayView? = null

    private var params: WindowManager.LayoutParams? = null

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

        startForeground(
            1,
            buildNotification()
        )

        windowManager =
            getSystemService(
                WINDOW_SERVICE
            ) as WindowManager
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        showRing()

        return START_STICKY
    }

    private fun showRing() {

        if (overlayView != null) {
            return
        }

        overlayView =
            RingOverlayView(this)

        params =
            WindowManager.LayoutParams(
                220,
                220,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
            )

        params?.gravity =
            Gravity.TOP or Gravity.START

        /*
         * OnePlus 12 Frontkamera
         * Startposition
         */

        params?.x = 900
        params?.y = 80

        windowManager.addView(
            overlayView,
            params
        )
    }

    fun hideRing() {

        overlayView?.let {

            windowManager.removeView(it)

            overlayView = null
        }
    }

    override fun onDestroy() {

        hideRing()

        super.onDestroy()
    }

    override fun onBind(
        intent: Intent?
    ): IBinder? = null

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel =
                NotificationChannel(
                    "ring_service",
                    "Ring Service",
                    NotificationManager.IMPORTANCE_LOW
                )

            val manager =
                getSystemService(
                    NotificationManager::class.java
                )

            manager.createNotificationChannel(
                channel
            )
        }
    }

    private fun buildNotification(): Notification {

        return Notification.Builder(
            this,
            "ring_service"
        )
            .setContentTitle(
                "Camera Ring aktiv"
            )
            .setSmallIcon(
                android.R.drawable.ic_dialog_info
            )
            .build()
    }
}