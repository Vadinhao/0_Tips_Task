package com.example.a0_tips_task.data

import com.example.a0_tips_task.R

data class onbordingData(val titleResId: Int, val imgResId: Int, val textResId: Int)

class dataSource() {
    fun loadData(): List<onbordingData> {
        return listOf<onbordingData>(
            onbordingData(
                R.string.title_for_onbording_1,
                R.drawable.onbording_step_1,
                R.string.text_for_onbording_1
            ),
            onbordingData(
                R.string.title_for_onbording_2,
                R.drawable.onbording_step_2,
                R.string.text_for_onbording_2
            ),
            onbordingData(
                R.string.title_for_onbording_3,
                R.drawable.onbording_step_3,
                R.string.text_for_onbording_3
            )
        )
    }
}