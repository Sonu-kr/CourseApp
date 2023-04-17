package com.example.reccardapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity
{
    Button btnNotes,btnVideos;
    Context context;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       ImageView img=(ImageView)findViewById(R.id.desc_img);
        TextView tv1=(TextView)findViewById(R.id.desc_header);
        TextView tv2=(TextView)findViewById(R.id.desc_desc);

        img.setImageResource(getIntent().getIntExtra("imagename",0));
        tv1.setText(getIntent().getStringExtra("header"));
        tv2.setText(getIntent().getStringExtra("desc"));
        btnNotes = findViewById(R.id.btnNotes);
        btnVideos =findViewById(R.id.btnVideos);
//        HashMap<String,String> h = null;
//        h.put("Java" ,"");
//        h.put("C++","");

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "Notes", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Main2Activity.this,Notes.class);
                startActivity(intent);
            }
        });
        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "Videos", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Main2Activity.this,WatchActivity.class);
                startActivity(intent);
            }
        });
    }
}
