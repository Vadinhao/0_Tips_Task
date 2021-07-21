package com.example.a0_tips_task

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.a0_tips_task.databinding.ActivityOnbordingBinding

@SuppressLint("ResourceAsColor")
class OnbordingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnbordingBinding
    //***
    private val APP_SETTINGS = "app_settings"
    private val HAS_VISITED = "has_visited"
    //***
    private val NUM_OF_PAGES = 3
    private val INDEX_OF_LAST_PAGE = NUM_OF_PAGES - 1
    private val INDEX_OF_FIRST_PAGE = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //***
        val startApp = Intent(this, MainActivity::class.java)
        //***
        val sharPref = getSharedPreferences(
            APP_SETTINGS,
            Context.MODE_PRIVATE
        )
        val hasVisited = sharPref.getBoolean(HAS_VISITED, false)
        if (hasVisited) {
            startActivity(startApp)
            finish()
        }
        //***
        setContentView(R.layout.activity_onbording)
        binding = ActivityOnbordingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //***
        binding.viewPager2.adapter = ViewPagerAdapter()
        binding.springDotsIndicator.setViewPager2(binding.viewPager2)
        //***
        binding.buttonBack.setOnClickListener {
            binding.viewPager2.setCurrentItem(binding.viewPager2.currentItem - 1, true)
        }
        //***
        binding.buttonSkip.setOnClickListener {
            val e: SharedPreferences.Editor = sharPref.edit()
            e.putBoolean(HAS_VISITED, true)
            e.apply()
            //***
            startActivity(startApp)
            finish()
        }
        //***
        binding.buttonNext.setOnClickListener {
            if (binding.viewPager2.currentItem < INDEX_OF_LAST_PAGE) {
                binding.viewPager2.setCurrentItem(binding.viewPager2.currentItem + 1, true)
            } else {
                val e: SharedPreferences.Editor = sharPref.edit()
                e.putBoolean(HAS_VISITED, true)
                e.apply()
                //***
                startActivity(startApp)
                finish()
            }
        }
        //***
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //Visibility of Skip button
                if (position in INDEX_OF_FIRST_PAGE until INDEX_OF_LAST_PAGE) {
                    binding.buttonSkip.visibility = View.VISIBLE
                } else {
                    binding.buttonSkip.visibility = View.INVISIBLE
                }
                //Visibility of Back button
                if (position in (INDEX_OF_FIRST_PAGE + 1)..INDEX_OF_LAST_PAGE) {
                    binding.buttonBack.visibility = View.VISIBLE
                } else {
                    binding.buttonBack.visibility = View.INVISIBLE
                }
            }
        })
    }
}