package com.cameraring.notifier.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.Gravity
import android.view.WindowManager

class CameraRingOverlayService : Service() {

    private lateinit var windowManager: WindowManager

    private var overlayView: RingOverlayView? = null

    private var params: WindowManager.LayoutParams? = null

    private val handler =
        Handler(Looper.getMainLooper())

    /*
     * Kann später aus den Einstellungen geladen werden
     */
    private var blinking = true

    /*
     * Blinkintervall in Millisekunden
     */
    private var blinkInterval = 1000L

    private val blinkRunnable =
        object : Runnable {

            override fun run() {

                overlayView?.let { view ->

                    view.visibleRing =
                        !view.visibleRing

                    view.invalidate()

                    handler.postDelayed(
                        this,
                        blinkInterval
                    )
                }
            }
        }

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
            RingOverlayView(this).apply {

                /*
                 * Standardwerte
                 * Später aus DataStore laden
                 */

                ringSize = 140f

                ringThickness = 10f

                ringColor =
                    android.graphics.Color.GREEN
            }

        params =
            WindowManager.LayoutParams(
                250,
                250,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
            )

        params?.gravity =
            Gravity.TOP or Gravity.START

        /*
         * OnePlus 12 Startposition
         * Kann später frei verschoben werden
         */

        params?.x = 900
        params?.y = 80

        windowManager.addView(
            overlayView,
            params
        )

        if (blinking) {

            handler.post(
                blinkRunnable
            )
        }
    }

    private fun hideRing() {

        handler.removeCallbacks(
            blinkRunnable
        )

        overlayView?.let {

            windowManager.removeView(it)

            overlayView = null
        }
    }

    override fun onDestroy() {

        hideRing()

        handler.removeCallbacksAndMessages(
            null
        )

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
                    "Camera Ring Service",
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

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Notification.Builder(
                this,
                "ring_service"
            )
                .setContentTitle(
                    "Camera Ring Notifier"
                )
                .setContentText(
                    "Benachrichtigungsring aktiv"
                )
                .setSmallIcon(
                    android.R.drawable.ic_dialog_info
                )
                .setOngoing(true)