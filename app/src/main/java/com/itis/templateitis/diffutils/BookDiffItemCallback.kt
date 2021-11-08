package com.itis.templateitis.diffutils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.itis.templateitis.model.Book

class BookDiffItemCallback : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(
        oldItem: Book,
        newItem: Book
    ): Boolean = oldItem.author == newItem.author

    override fun areContentsTheSame(
        oldItem: Book,
        newItem: Book
    ): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: Book, newItem: Book): Any? {
        val bundle = Bundle()
        if (oldItem.title != newItem.title) {
            bundle.putString("TITLE", newItem.title)
        }
        if (oldItem.author != newItem.author) {
            bundle.putString("AUTHOR", newItem.author)
        }
        if (oldItem.url != newItem.url) {
            bundle.putString("URL", newItem.url)
        }
        if (bundle.isEmpty) return null
        return bundle
    }
}
