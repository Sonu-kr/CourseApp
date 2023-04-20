package com.example.reccardapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {
    lateinit var btnNotes: Button
    lateinit var btnVideos: Button
    //    var btnNotes: Button? = null
//    var btnVideos: Button? = null
    var context: Context? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val img = findViewById<View>(R.id.desc_img) as ImageView
        val tv1 = findViewById<View>(R.id.desc_header) as TextView
        val tv2 = findViewById<View>(R.id.desc_desc) as TextView
        img.setImageResource(intent.getIntExtra("imagename", 0))
        tv1.text = intent.getStringExtra("header")
        tv2.text = intent.getStringExtra("desc")
        btnNotes = findViewById(R.id.btnNotes)
        btnVideos = findViewById(R.id.btnVideos)
        //        HashMap<String,String> h = null;
//        h.put("Java" ,"");
//        h.put("C++","");

        btnNotes.setOnClickListener {
            Toast.makeText(this@Main2Activity, "Notes", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Main2Activity, Notes::class.java)
            startActivity(intent)
        }

        btnVideos.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@Main2Activity, "Videos", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Main2Activity, WatchActivity::class.java)
            startActivity(intent)
        })
    }
}