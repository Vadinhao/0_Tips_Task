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
import com.example.a0_tips_task.databinding.ActivityOnboardingBinding

@SuppressLint("ResourceAsColor")
class OnboardingActivity : AppCompatActivity() {

    /** lateinit value:
     *[binding] for views on OnbordingActivity*/
    private lateinit var binding: ActivityOnboardingBinding

    /** lateinit value:
     *  [startApp] for go to MainActivity*/
    private lateinit var startApp: Intent

    /** lateinit value:
     *  [sharPref] for save data about reading onboarding*/
    private lateinit var sharPref: SharedPreferences

    /** value:
     * [APP_SETTINGS] for access to app_settings*/
    private val APP_SETTINGS = "app_settings"

    /** value:
     * [HAS_VISITED] for check or set value which means: was onboarding read or not*/
    private val HAS_VISITED = "has_visited"

    //values for registerOnPageChangeCallback()
    private val NUM_OF_PAGES = 3
    private val INDEX_OF_LAST_PAGE = NUM_OF_PAGES - 1
    private val INDEX_OF_FIRST_PAGE = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //init lateinit values
        startApp = Intent(this, MainActivity::class.java)
        sharPref = getSharedPreferences(
            APP_SETTINGS,
            Context.MODE_PRIVATE
        )
        //checking if onboadrding has read earlier
        val hasVisited = sharPref.getBoolean(HAS_VISITED, false)
        if (hasVisited) {
            goToMainActivity()
        }
        //setup onboarding activity
        setContentView(R.layout.activity_onboarding)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //call fun for setup ViewPager and ClickListener-s for navigate buttons(back, skip and next)
        setupViewPager()
        setupClicks()
    }

    private fun goToMainActivity(){
        startActivity(startApp)
        finish()
    }

    private fun setupViewPager(){
        //setup adapter for ViewPager
        binding.viewPager.adapter = ViewPagerAdapter()
        //setup DotsIndicator for viewPager
        binding.springDotsIndicator.setViewPager2(binding.viewPager)
        //setup OnPageChangeCallback for viewPager
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
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

    private fun setupClicks(){
        //clickListener for "Back" button
        binding.buttonBack.setOnClickListener {
            //go to the previous page
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
        }
        //clickListener for "Skip" button
        binding.buttonSkip.setOnClickListener {
            //save that onboarding has been read
            editSharedPreferences()
            //go to MainActivity
            goToMainActivity()
        }
        //clickListener for "Next" button
        binding.buttonNext.setOnClickListener {
            if (binding.viewPager.currentItem < INDEX_OF_LAST_PAGE) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
            } else {
                //save that onboarding has been read
                editSharedPreferences()
                //go to MainActivity
                goToMainActivity()
            }
        }
    }

    private fun editSharedPreferences(){
        //create editor->put preference->apply changes
        val e: SharedPreferences.Editor = sharPref.edit()
        e.putBoolean(HAS_VISITED, true)
        e.apply()
    }
}