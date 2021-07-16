package com.example.a0_tips_task

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.a0_tips_task.databinding.ActivityOnbordingBinding


@SuppressLint("ResourceAsColor")
class OnbordingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnbordingBinding
    private val APP_SETTINGS = "app_settings"

    private fun getViewPagerItem(i: Int): Int {
        return binding.viewPager2.currentItem + i
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //***
        val startApp = Intent(this, MainActivity::class.java)
        val sharPref = getSharedPreferences(
            APP_SETTINGS,
            Context.MODE_PRIVATE
        )
        val hasVisited = sharPref.getBoolean("hasVisited", false)

        if (!hasVisited) {
            val e: SharedPreferences.Editor = sharPref.edit()
            e.putBoolean("hasVisited", true)
            e.apply()
        } else {
            startActivity(startApp)
            finish()
        }
        //***
        setContentView(R.layout.activity_onbording)
        binding = ActivityOnbordingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.adapter = ViewPagerAdapter()

        binding.buttonBack.setOnClickListener {
            binding.viewPager2.setCurrentItem(getViewPagerItem(-1), true)
        }

        binding.buttonSkip.setOnClickListener {
            startActivity(startApp)
            finish()
        }

        //костыль(2-ка)
        binding.buttonNext.setOnClickListener {

            if (getViewPagerItem(0) < 2) {
                binding.viewPager2.setCurrentItem(getViewPagerItem(1), true)
            } else {
                startActivity(startApp)
                finish()
            }
        }
        //костыль(промеж)
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position in 0..1) {
                    binding.buttonSkip.visibility = View.VISIBLE
                } else {
                    binding.buttonSkip.visibility = View.INVISIBLE
                }

                if (position in 1..2) {
                    binding.buttonBack.visibility = View.VISIBLE
                } else {
                    binding.buttonBack.visibility = View.INVISIBLE
                }

                setUpDots(position)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    fun setUpDots(position: Int) {

        val dots: Array<TextView> = arrayOf(
            this.layoutInflater.inflate(R.xml.text_view, null) as TextView,
            this.layoutInflater.inflate(R.xml.text_view, null) as TextView,
            this.layoutInflater.inflate(R.xml.text_view, null) as TextView
        )

        binding.linerLayout.removeAllViews()

        for (i in 0..2) {
            dots[i].text = getString(R.string.dot_for_onbording)
            dots[i].textSize = 35F
            dots[i].setTextColor(getColor(R.color.onborging_gray))
            binding.linerLayout.addView(dots[i])
        }

        dots[position].setTextColor(getColor(R.color.onborging_green))
    }
}