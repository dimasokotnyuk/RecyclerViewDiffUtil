package com.example.recyclerviewdiffutil

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_random.view.*

class RandomViewHolder(private val listItemView: View) : RecyclerView.ViewHolder(listItemView) {
    fun bindValue(item: RandomValue) {
        listItemView.tvRandom.text = item.value.toString()
    }
}