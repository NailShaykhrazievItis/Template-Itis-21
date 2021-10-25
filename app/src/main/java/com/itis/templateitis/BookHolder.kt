package com.itis.templateitis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.templateitis.databinding.ItemBookBinding

class BookHolder(
    private val binding: ItemBookBinding,
    private val glide: RequestManager,
    private val action: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var book: Book? = null

    init {
        // use only one [setOnClickListener]
        itemView.setOnClickListener {
            book?.title?.also(action)
        }
    }

    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun bind(item: Book) {
        this.book = item
        with(binding) {
            tvTitle.text = item.title
            tvDesc.text = item.author

            tvTitle.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    android.R.color.holo_red_dark
                )
            )
            glide.load(item.url)
                .apply(options)
                .into(ivImage)

            // for locale resources
            // ivImage.setImageResource(R.drawable.ic_launcher_background)
        }


        // use only one [setOnClickListener]
        itemView.setOnClickListener {
            action(item.title)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            action: (String) -> Unit
        ) = BookHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide, action)
    }
}
