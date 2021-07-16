package com.example.a0_tips_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a0_tips_task.data.dataSource

class ViewPagerAdapter() : RecyclerView.Adapter<ViewPagerAdapter.ScreenViewHolder>() {

    private var data = dataSource().loadData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.slider_layout, parent, false)
        return ScreenViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ScreenViewHolder, position: Int) {
        val context = holder.titleView.context
        holder.titleView.text = context.getString(data[position].titleResId)
        holder.imgView.setImageResource(data[position].imgResId)
        holder.textView.text = context.getString(data[position].textResId)
    }

    class ScreenViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var titleView: TextView = view.findViewById<TextView>(R.id.textView_title)
        var textView: TextView = view.findViewById<TextView>(R.id.textView_text)
        var imgView: ImageView = view.findViewById<ImageView>(R.id.imageView)
    }
}