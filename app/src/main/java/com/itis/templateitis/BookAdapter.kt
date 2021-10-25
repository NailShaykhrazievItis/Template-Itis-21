package com.itis.templateitis

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager

class BookAdapter(
    private val list: List<Book>,
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

    override fun getItemCount(): Int = list.size
}
