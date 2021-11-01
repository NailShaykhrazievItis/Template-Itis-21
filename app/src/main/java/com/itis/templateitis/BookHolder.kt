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
import com.itis.templateitis.databinding.ItemCvBookBinding

class BookHolder(
    private val binding: ItemCvBookBinding,
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

    fun updateTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun updateAuthor(title: String) {
        binding.tvDesc.text = title
    }

    companion object {

        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            action: (String) -> Unit
        ) = BookHolder(
            ItemCvBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide, action)
    }
}
