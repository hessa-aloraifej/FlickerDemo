package com.example.flickerdemo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(val main:MainActivity, val list: List<Photo>?=null,val data: List<Flicker>?=null) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = list?.get(position)
        val title=photo?.title

        var counter=0

        holder.itemView.apply {
            if (photo != null) {
                textView.text = " Name: ${photo.title}"
            }
            val url="https://live.staticflickr.com/${photo?.server}/${photo?.id}_${photo?.secret}_s.png"
            Glide.with(holder.itemView.context)
                .load("https://live.staticflickr.com/${photo?.server}/${photo?.id}_${photo?.secret}_s.png")
                .into(image)
            llcard.setOnClickListener {
                val intent=Intent(main,Show::class.java)
               intent.putExtra("url",url)
                main.startActivity(intent)
                Toast.makeText(holder.itemView.context, "", Toast.LENGTH_SHORT).show()
            }

            imgbtn.setOnClickListener{
                counter+=1
                val obj=Flicker(0, title.toString(),url)
                if(counter%2==0){
                    imgbtn.setBackgroundColor(Color.BLACK)
                    main.save(obj)
                }
                else{
                    imgbtn.setBackgroundColor(Color.WHITE)
                    if (data != null) {
                       // val objj=Flicker(0-1, title.toString(),url)
                        main.remove(obj)
                    }
                }

            }

        }
    }

    override fun getItemCount(): Int = list?.size!!
}

//https://live.staticflickr.com/{server-id}/{id}_{o-secret}_o.{o-format}