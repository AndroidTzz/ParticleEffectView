package com.zero.tzz.particleeffect

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 *
 * @author lucy
 * @date 2019-06-17 17:27
 * @description //TODO
 */
class ParticleEffectView : View {

    private lateinit var mPaint: Paint
    private val list: ArrayList<Ball> = arrayListOf()

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context?, attributeSet: AttributeSet?, defStyle: Int) : super(context, attributeSet, defStyle)

    init {
        mPaint = Paint()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                createBall(event)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                createBall(event)
                return true
            }

        }
        return super.onTouchEvent(event)
    }

    private fun createBall(event: MotionEvent) {
        val x = event.x.toInt()
        val y = event.y.toInt()
        for (i in 1..50) {
            val rx = RandomUtil.randomInt(-5, 5) + x
            val ry = RandomUtil.randomInt(-5, 5) + y
            val ball = Ball(RandomUtil.randomColor(), RandomUtil.randomInt(2, 4), point = Point(rx, ry))
            list.add(ball)
        }
        if (list.size == 50) {
            invalidate()
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.BLACK)
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val ball = iterator.next()
            if (ball.alpha > 0) {
                mPaint.color = ball.color
                mPaint.alpha = ball.alpha
                canvas?.drawCircle(ball.point.x.toFloat(), ball.point.y.toFloat(), ball.radius.toFloat(), mPaint)
                ball.alpha = ball.alpha - 10
                ball.radius = ball.radius + 1
                val rx = RandomUtil.randomInt(-30, 30) + ball.point.x
                val ry = RandomUtil.randomInt(-30, 30) + ball.point.y
                ball.point.x = rx
                ball.point.y = ry
            } else {
                iterator.remove()
            }
        }
        if (list.size > 0) {
            invalidate()
        }
    }
}