package com.example.a0_tips_task.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a0_tips_task.R

enum class Pages(
    @StringRes val titleResId: Int,
    @DrawableRes  val imgResId: Int,
    @StringRes val textResId: Int
) {
    //Page_one
    STEP_ONE(
        R.string.title_for_onboarding_1,
        R.drawable.onboarding_step_1,
        R.string.text_for_onboarding_1
    ),

    //Page_two
    STEP_TWO(
        R.string.title_for_onboarding_2,
        R.drawable.onboarding_step_2,
        R.string.text_for_onboarding_2
    ),

    //Page_three
    STEP_THREE(
        R.string.title_for_onboarding_3,
        R.drawable.onboarding_step_3,
        R.string.text_for_onboarding_3
    )
}