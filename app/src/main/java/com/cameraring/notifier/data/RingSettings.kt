package com.cameraring.notifier.data

import androidx.compose.ui.graphics.Color

data class RingSettings(

    val enabled: Boolean = true,

    val color: Long = 0xFF00FF00,

    val blinking: Boolean = true,

    val blinkInterval: Long = 1000L,

    val ringSize: Float = 120f,

    val ringThickness: Float = 8f,

    val alpha: Float = 1f,

    val autoCameraPosition: Boolean = true,

    val posX: Float = 930f,

    val posY: Float = 120f
) {
    fun composeColor(): Color {
        return Color(color)
    }
}