package com.example.custoprogressbarapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.custoprogressbarapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.setPercentButton.setOnClickListener {
            if (binding.percentOfProgressEditText.text.toString() == "") {
                binding.customProgressBar.progress = 0
                binding.customProgressBar.progressAnimationStartPoint = 0
            } else {
                binding.customProgressBar.progress =
                    Integer.parseInt(binding.percentOfProgressEditText.text.toString())
                binding.customProgressBar.progressAnimation()
            }
        }
    }
}