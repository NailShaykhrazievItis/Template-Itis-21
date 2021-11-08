package com.itis.templateitis.header

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.templateitis.adapter.BookHolder
import com.itis.templateitis.R
import java.lang.IllegalStateException
import java.lang.RuntimeException

class BookWithHeaderAdapter(
    private val list: ArrayList<BookWrapper>,
    private val glide: RequestManager,
    private val action: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (viewType) {
        R.layout.item_title -> TitleHolder.create(parent)
        R.layout.item_book -> BookHolder.create(parent, glide, action)
        else -> throw IllegalStateException("ERROR")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TitleHolder -> holder.bind(list[position].title.orEmpty())
            is BookHolder -> holder.bind(list[position].book ?: throw RuntimeException())
            else -> {
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = when {
        list[position].title != null -> R.layout.item_title
        list[position].book != null -> R.layout.item_book
        else -> throw IllegalStateException("ERROR")
    }
}
