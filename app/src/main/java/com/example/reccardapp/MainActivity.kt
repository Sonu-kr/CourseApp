package com.example.reccardapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var rcv: RecyclerView? = null
    var adapter: myadapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Recycler and Card View Demo"
        rcv = findViewById<View>(R.id.recview) as RecyclerView
        //  rcv.setLayoutManager(new LinearLayoutManager(this));
        adapter = myadapter(dataqueue(), applicationContext)
        rcv!!.adapter = adapter

        //LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //rcv.setLayoutManager(layoutManager);
        val gridLayoutManager = GridLayoutManager(this, 2)
        rcv!!.layoutManager = gridLayoutManager
    }

    fun dataqueue(): ArrayList<Model> {
        val holder = ArrayList<Model>()
        val ob1 = Model()
        ob1.header = "C Programming"
        ob1.desc = "Desktop Programming"
        ob1.imgname = R.drawable.cp
        holder.add(ob1)
        val ob2 = Model()
        ob2.header = "C++ Programming"
        ob2.desc = "Desktop Progamming Language"
        ob2.imgname = R.drawable.cpp
        holder.add(ob2)
        val ob3 = Model()
        ob3.header = "Java Programming"
        ob3.desc = "Desktop Progamming Language"
        ob3.imgname = R.drawable.java
        holder.add(ob3)
        val ob4 = Model()
        ob4.header = "PHP Programming"
        ob4.desc = "Web Progamming Language"
        ob4.imgname = R.drawable.php
        holder.add(ob4)
        val ob5 = Model()
        ob5.header = ".NET Programming"
        ob5.desc = "Desktop/Web Progamming Language"
        ob5.imgname = R.drawable.dotnet
        holder.add(ob5)
        val ob6 = Model()
        ob6.header = "Wordpress Framework"
        ob6.desc = "PHP based Blogging Framework"
        ob6.imgname = R.drawable.wordpress
        holder.add(ob6)
        val ob7 = Model()
        ob7.header = "Magento Framework"
        ob7.desc = "PHP Based e-Comm Framework"
        ob7.imgname = R.drawable.magento
        holder.add(ob7)
        val ob8 = Model()
        ob8.header = "Shopify Framework"
        ob8.desc = "PHP based e-Comm Framework"
        ob8.imgname = R.drawable.shopify
        holder.add(ob8)
        val ob9 = Model()
        ob9.header = "Angular Programming"
        ob9.desc = "Web Programming"
        ob9.imgname = R.drawable.angular
        holder.add(ob9)
        val ob10 = Model()
        ob10.header = "Python Programming"
        ob10.desc = "Desktop/Web based Progamming"
        ob10.imgname = R.drawable.python
        holder.add(ob10)
        val ob11 = Model()
        ob11.header = "Node JS Programming"
        ob11.desc = "Web based Programming"
        ob11.imgname = R.drawable.nodejs
        holder.add(ob11)
        return holder
    }
}