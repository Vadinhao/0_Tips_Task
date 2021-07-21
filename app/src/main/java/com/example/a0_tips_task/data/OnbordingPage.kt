package com.example.a0_tips_task.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a0_tips_task.R

enum class Pages(
    @StringRes val titleResId: Int,
    @StringRes val imgResId: Int,
    @DrawableRes val textResId: Int
) {
    //Page_one
    STEP_ONE(
        R.string.title_for_onbording_1,
        R.drawable.onbording_step_1,
        R.string.text_for_onbording_1
    ),

    //Page_two
    STEP_TWO(
        R.string.title_for_onbording_2,
        R.drawable.onbording_step_2,
        R.string.text_for_onbording_2
    ),

    //Page_three
    STEP_THREE(
        R.string.title_for_onbording_3,
        R.drawable.onbording_step_3,
        R.string.text_for_onbording_3
    )
}