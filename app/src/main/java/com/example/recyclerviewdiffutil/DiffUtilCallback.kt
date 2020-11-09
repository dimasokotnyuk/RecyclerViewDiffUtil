package com.example.recyclerviewdiffutil

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val oldList: List<RandomValue>,
    private val newList: List<RandomValue>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPos: Int, newItemPos: Int): Boolean {
        return oldList[oldItemPos].randomValue == newList[newItemPos].randomValue
    }

    override fun areContentsTheSame(oldItemPos: Int, newItemPos: Int): Boolean {
        return oldList[oldItemPos] == newList[newItemPos]
    }
}