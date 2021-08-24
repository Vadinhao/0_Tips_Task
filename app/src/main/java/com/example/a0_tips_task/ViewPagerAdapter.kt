package com.example.a0_tips_task

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a0_tips_task.data.Pages

class ViewPagerAdapter() : RecyclerView.Adapter<ViewPagerAdapter.ScreenViewHolder>() {

    private var data : Array<Pages> = Pages.values()

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
        holder.bind(data[position])
    }

    class ScreenViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var titleView: TextView = view.findViewById<TextView>(R.id.textView_title)
        var textView: TextView = view.findViewById<TextView>(R.id.textView_text)
        var imgView: ImageView = view.findViewById<ImageView>(R.id.imageView)
        @SuppressLint("ResourceType")
        fun bind(item: Pages){
            titleView.text = itemView.context.getString(item.titleResId)
            textView.text = itemView.context.getString(item.textResId)
            imgView.setImageResource(item.imgResId)
        }
    }
}