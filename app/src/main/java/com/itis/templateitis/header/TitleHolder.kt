package com.itis.templateitis.header

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.templateitis.databinding.ItemTitleBinding

class TitleHolder(
    private val binding: ItemTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(title: String) {
        binding.tvTitle.text = title
    }

    companion object {

        fun create(
            parent: ViewGroup,
        ) = TitleHolder(
            ItemTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
