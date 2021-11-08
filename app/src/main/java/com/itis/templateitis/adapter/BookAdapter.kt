package com.itis.templateitis.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.templateitis.diffutils.BookDiffUtils
import com.itis.templateitis.model.Book

class BookAdapter(
    private val list: ArrayList<Book>,
    private val glide: RequestManager,
    private val action: (String) -> Unit
) : RecyclerView.Adapter<BookHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookHolder = BookHolder.create(parent, glide, action)

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onBindViewHolder(
        holder: BookHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.last().takeIf { it is Bundle }?.let {
                it as Bundle
            }?.run {
                getString("TITLE")?.also {
                    holder.updateTitle(it)
                }
                getString("AUTHOR")?.also {
                    holder.updateAuthor(it)
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateCurrentItem(book: Book, position: Int) {
        if (position >= itemCount) return

        val bundle = Bundle()
        list[position].also { oldBook ->
            if (oldBook.title != book.title) {
                bundle.putString("TITLE", book.title)
            }
            if (oldBook.author != book.author) {
                bundle.putString("AUTHOR", book.author)
            }
        }

        list[position] = book

        notifyItemChanged(position, bundle)
        notifyItemChanged(position, bundle)
        notifyItemChanged(position, bundle)
        notifyItemChanged(position, bundle)
        notifyItemChanged(position, bundle)
        notifyItemChanged(position, bundle)
    }

    fun updateData(newList: List<Book>) {
        val callback = BookDiffUtils(list, newList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)

        list.clear()
        list.addAll(newList)
    }
}
