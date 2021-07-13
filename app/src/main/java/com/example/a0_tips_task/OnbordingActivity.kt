package com.example.a0_tips_task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a0_tips_task.databinding.ActivityOnbordingBinding

class OnbordingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnbordingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onbording)

        binding = ActivityOnbordingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.adapter = ViewPagerAdapter()

        binding.buttonBack.setOnClickListener{

        }

        binding.buttonSkip.setOnClickListener {

        }

        binding.buttonNext.setOnClickListener {

        }

        //adding dots
    }
}