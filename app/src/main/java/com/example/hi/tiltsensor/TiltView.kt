package com.example.hi.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        //녹색 페인트
        greenPaint.color = Color.GREEN
        //검은색 페인트
        blackPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w/2f
        cY = h/2f
    }



    override fun onDraw(canvas: Canvas?) {
        //그리기
        //바깥원
        canvas?.drawCircle(cX, cY, 100f, blackPaint)
        //녹색원
        canvas?.drawCircle(xCoord + cX,yCoord + cY, 100f, greenPaint)
        //가운데 십자가
        canvas?.drawLine(cX-20, cY, cX+20, cY, blackPaint)
        canvas?.drawLine(cX, cY-20, cX, cY + 20, blackPaint)
    }

    fun onSensorEvent(event: SensorEvent){
        //화면을 가로로 돌려서 x축과 y축이 바뀜.
        yCoord = event.values[0] *20
        xCoord = event.values[1] *20

        invalidate()
    }
}