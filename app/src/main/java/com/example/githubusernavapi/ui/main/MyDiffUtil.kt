package com.example.githubusernavapi.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.example.githubusernavapi.data.model.User

class MyDiffUtil(
    private val oldList: ArrayList<User>,
    private val newList: ArrayList<User>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {false}
            oldList[oldItemPosition].login == newList[newItemPosition].login -> {false}
            else -> true
        }
    }
}