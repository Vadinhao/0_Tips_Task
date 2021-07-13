package com.example.a0_tips_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter() : RecyclerView.Adapter<ViewPagerAdapter.ScreenViewHolder>() {

    private val titles: ArrayList<Int> = arrayListOf(
        R.string.title_for_onbording_1,
        R.string.title_for_onbording_2,
        R.string.title_for_onbording_3
    )

    private val images: ArrayList<Int> = arrayListOf(
        R.drawable.onbording_step_1,
        R.drawable.onbording_step_2,
        R.drawable.onbording_step_3
    )

    private val texts: ArrayList<Int> = arrayListOf(
        R.string.text_for_onbording_1,
        R.string.text_for_onbording_2,
        R.string.text_for_onbording_3
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.slider_layout, parent, false)
        return ScreenViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ScreenViewHolder, position: Int) {
        val context = holder.titleView.context
        holder.titleView.text = context.getString(titles[position]);
        holder.imgView.setImageResource(images[position])
        holder.textView.text = context.getString(texts[position]);
    }

    class ScreenViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var titleView = view.findViewById<TextView>(R.id.textView_title)
        var textView = view.findViewById<TextView>(R.id.textView_text)
        var imgView = view.findViewById<ImageView>(R.id.imageView)
    }
}