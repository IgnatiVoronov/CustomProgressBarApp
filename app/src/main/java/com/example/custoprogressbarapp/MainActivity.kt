package com.example.custoprogressbarapp

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customProgressBar = findViewById<CustomProgressBar>(R.id.customProgressBar)
        val percentOfProgressEditText = findViewById<EditText>(R.id.percentOfProgressEditText)
        val setPercentButton = findViewById<Button>(R.id.setPercentButton)
        var valueAnimator: ValueAnimator
        var startPoint = 0

        setPercentButton.setOnClickListener {
            if (percentOfProgressEditText.text.toString() == "") {
                customProgressBar.progress = 0
                startPoint = 0
            } else {
                customProgressBar.progress =
                    Integer.parseInt(percentOfProgressEditText.text.toString())

                valueAnimator = ValueAnimator.ofInt(startPoint, customProgressBar.progress)
                valueAnimator.addUpdateListener {
                    val n = it.animatedValue as Int
                    val R = (255 * n) / 100
                    val G = (255 * (100 - n)) / 100
                    val B = 0
                    customProgressBar.progressColor = Color.rgb(R, G, B)
                    customProgressBar.progress = n
                    startPoint = customProgressBar.progress
                }

                valueAnimator.duration = 2_000
                valueAnimator.start()
            }
        }
    }
}