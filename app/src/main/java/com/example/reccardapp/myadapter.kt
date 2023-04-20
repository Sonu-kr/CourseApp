package com.example.reccardapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class myadapter(var data: ArrayList<Model>, var context: Context) :
    RecyclerView.Adapter<myviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.singlerow, parent, false)
        return myviewholder(view)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val temp = data[position]
        holder.t1.text = data[position].header
        holder.t2.text = data[position].desc
        holder.img.setImageResource(data[position].imgname)
        holder.img.setOnClickListener {
            val intent = Intent(context, Main2Activity::class.java)
            intent.putExtra("imagename", temp.imgname)
            intent.putExtra("header", temp.header)
            intent.putExtra("desc", temp.desc)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}