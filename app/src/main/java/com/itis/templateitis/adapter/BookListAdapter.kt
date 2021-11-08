package com.itis.templateitis.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.itis.templateitis.diffutils.BookDiffItemCallback
import com.itis.templateitis.model.Book

class BookListAdapter(
    private val glide: RequestManager,
    private val action: (String) -> Unit
) : ListAdapter<Book, BookHolder>(BookDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookHolder = BookHolder.create(parent, glide, action)

    override fun onBindViewHolder(
        holder: BookHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
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
                holder.updateFields(it as Bundle)
            }
        }
    }

    override fun submitList(list: MutableList<Book>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}
