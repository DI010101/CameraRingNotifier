package com.cameraring.notifier.service

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(
        sbn: StatusBarNotification?
    ) {

        if (sbn == null) return

        val packageName = sbn.packageName

        /*
         * Eigene Benachrichtigungen ignorieren
         */
        if (packageName == applicationContext.packageName) {
            return
        }

        val serviceIntent =
            Intent(
                this,
                CameraRingOverlayService::class.java
            )

        startForegroundService(serviceIntent)
    }

    override fun onNotificationRemoved(
        sbn: StatusBarNotification?
    ) {

        /*
         * Einfachste Variante:
         * Ring stoppen sobald eine Notification
         * entfernt wird.
         *
         * Später können wir zählen,
         * ob noch ungelesene Notifications
         * vorhanden sind.
         */

        stopService(
            Intent(
                this,
                CameraRingOverlayService::class.java
            )
        )
    }
}