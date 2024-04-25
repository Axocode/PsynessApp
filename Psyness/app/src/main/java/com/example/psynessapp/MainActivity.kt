package com.example.psynessapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import java.util.Timer
import java.util.TimerTask

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tarea: TimerTask = object : TimerTask() {
            override fun run() {
                val intento = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intento)
                finish()
            }
        }
        val tiempo = Timer()
        tiempo.schedule(tarea, 2000)
    }
}
