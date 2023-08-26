package com.example.custoprogressbarapp

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.custoprogressbarapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        var valueAnimator: ValueAnimator
        var startPoint = 0
        setContentView(view)


        binding.setPercentButton.setOnClickListener {
            if (binding.percentOfProgressEditText.text.toString() == "") {
                binding.customProgressBar.progress = 0
                startPoint = 0
            } else {
                binding.customProgressBar.progress =
                    Integer.parseInt(binding.percentOfProgressEditText.text.toString())

                valueAnimator = ValueAnimator.ofInt(startPoint, binding.customProgressBar.progress)
                valueAnimator.addUpdateListener {
                    val n = it.animatedValue as Int
                    val R = (255 * n) / 100
                    val G = (255 * (100 - n)) / 100
                    val B = 0
                    binding.customProgressBar.progressColor = Color.rgb(R, G, B)
                    binding.customProgressBar.progress = n
                    startPoint = binding.customProgressBar.progress
                }

                valueAnimator.duration = 2_000
                valueAnimator.start()
            }
        }
    }
}