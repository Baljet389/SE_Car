package com.example.controlrccar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class CustomWheelView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    //use numbers where 360 % numberLines = 0
    private val numberLines = 3
    private val wheelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        strokeWidth = 20f
    }

    private val indicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 10f
    }

    private var currentAngle: Float = 0f

    var onWheelChangeListener: ((Float) -> Unit)? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = min(centerX, centerY) - 20f

        // Draw the wheel
        canvas.drawCircle(centerX, centerY, radius, wheelPaint)
        val angleBetLines: Int = 360/numberLines
        // Draw the indicator line
        for (i in 1..numberLines) {
            val indicatorX =
                (centerX + radius * cos(Math.toRadians((currentAngle+i*angleBetLines).toDouble()))).toFloat()
            val indicatorY =
                (centerY + radius * sin(Math.toRadians((currentAngle+i*angleBetLines).toDouble()))).toFloat()
            canvas.drawLine(centerX, centerY, indicatorX, indicatorY, indicatorPaint)
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val centerX = width / 2f
        val centerY = height / 2f
        val x = event.x
        val y = event.y

        if (event.action == MotionEvent.ACTION_MOVE) {
            val angle = Math.toDegrees(atan2((y - centerY).toDouble(), (x - centerX).toDouble()))
            currentAngle = (if (angle < 0) angle + 360 else angle).toFloat()
            invalidate()

            onWheelChangeListener?.invoke(currentAngle)
        }
        return true
    }
}