package com.cameraring.notifier.service

import android.content.Context
import android.graphics.*
import android.view.View

class RingOverlayView(
    context: Context
) : View(context) {

    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.GREEN
        strokeWidth = 12f
        isAntiAlias = true
    }

    var ringSize = 120f
        set(value) {
            field = value
            invalidate()
        }

    var ringThickness = 8f
        set(value) {
            field = value
            paint.strokeWidth = value
            invalidate()
        }

    var ringColor = Color.GREEN
        set(value) {
            field = value
            paint.color = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val radius = ringSize / 2f

        canvas.drawCircle(
            width / 2f,
            height / 2f,
            radius,
            paint
        )
    }
}