package com.example.reccardapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var img: ImageView
    var t1: TextView
    var t2: TextView

    init {
        img = itemView.findViewById<View>(R.id.img1) as ImageView
        t1 = itemView.findViewById<View>(R.id.t1) as TextView
        t2 = itemView.findViewById<View>(R.id.t2) as TextView
    }
}