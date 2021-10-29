package com.example.flickerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class Show : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        var img=findViewById<ImageView>(R.id.show)
        val url=intent.extras?.getString("url"," ")
        Glide.with(this)
            .load(url)
            .into(img)
    }
}