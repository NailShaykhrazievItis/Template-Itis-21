package com.itis.templateitis.diffutils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.itis.templateitis.model.Book

class BookDiffUtils(
    private val oldList: List<Book>,
    private val newList: List<Book>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldList[oldItemPosition].title ==
            newList[newItemPosition].title

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val bundle = Bundle()
        val oldBook = oldList[oldItemPosition]
        val newBook = newList[newItemPosition]

        if (oldBook.title != newBook.title) {
            bundle.putString("TITLE", newBook.title)
        }
        if (oldBook.author != newBook.author) {
            bundle.putString("AUTHOR", newBook.author)
        }

        if (bundle.isEmpty) return null
        return bundle
    }
}
