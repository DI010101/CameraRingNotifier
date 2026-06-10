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

        strokeWidth = 10f

        isAntiAlias = true
    }

    var ringSize = 120f

    var ringThickness = 10f

    var ringColor = Color.GREEN

    var visibleRing = true

    override fun onDraw(
        canvas: Canvas
    ) {

        super.onDraw(canvas)

        if (!visibleRing) {
            return
        }

        paint.color = ringColor

        paint.strokeWidth = ringThickness

        canvas.drawCircle(
            width / 2f,
            height / 2f,
            ringSize / 2f,
            paint
        )
    }
}