package com.example.recyclerviewdiffutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class ListAdapterTest : ListAdapter<RandomValue, RandomViewHolder>(DiffUtilCallbackTest()) {

    companion object {
        class DiffUtilCallbackTest : DiffUtil.ItemCallback<RandomValue>() {
            override fun areItemsTheSame(oldItem: RandomValue, newItem: RandomValue): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RandomValue, newItem: RandomValue): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomViewHolder {
        return RandomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_random, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RandomViewHolder, position: Int) {
        holder.bindValue(getItem(position))
    }
}