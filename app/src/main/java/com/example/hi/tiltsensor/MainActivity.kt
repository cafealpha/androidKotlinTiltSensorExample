package com.example.hi.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity() , SensorEventListener {
    private lateinit var tiltView: TiltView


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        //센서값이 변경되면 호출됨
        // values[0] : x축값 : 위로 기울이면 -10~0, 아래로 기울이면 0-10
        // values[1] : y축값 : 왼쪽 -10-0, 오른쪽 0-10
        // values[2] : z축값 : 미사용
        event?.let{
            Log.d("MainActivity", "onSensorChanged: x : " +
                    "${event.values[0]}, y : ${event.values[1]}, z: ${event.values[2]}")
            tiltView.onSensorEvent(event)

        }
    }

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //화면 항상 켜지
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        //가로모드로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        super.onCreate(savedInstanceState)
        tiltView = TiltView(this)

        setContentView(tiltView)

        //setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}
