package com.example.flickerdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(val list: List<Photo>) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
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

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val photo = list[position]

        holder.itemView.apply {
            textView.text = " Name: ${photo.title}"
            Glide.with(holder.itemView.context)
                .load("https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_s.png")
                .into(image)
            llcard.setOnClickListener {
                Toast.makeText(holder.itemView.context, "", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = list.size
}

//https://live.staticflickr.com/{server-id}/{id}_{o-secret}_o.{o-format}