package com.example.recyclerviewdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_random.view.*


class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: MutableList<RandomValue> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RandomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_random, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as RandomViewHolder
        holder.bindValue(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(newData: ArrayList<RandomValue>) {
        var diffResult = DiffUtil.calculateDiff(DiffUtilCallback(newData, data))
        diffResult.dispatchUpdatesTo(this)
        data.clear()
        this.data.addAll(newData)
    }

    fun remove(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun onItemMoved(fromPosition: Int, toPosition: Int) {
        val oldItem = data[toPosition]
        val newItem = data[fromPosition]
        data[fromPosition] = oldItem
        data[toPosition] = newItem

        notifyItemMoved(fromPosition, toPosition)
    }

}