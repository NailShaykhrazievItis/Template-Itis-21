package com.itis.templateitis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class MainFragment: Fragment(R.layout.fragment_main) {

    private var bookAdapter: BookAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter(BookRepository.books, Glide.with(this)) {
            showSelectedTitle(it)
        }
        view.findViewById<RecyclerView>(R.id.rv_books).run {
            adapter = bookAdapter
        }
    }

    private fun showSelectedTitle(title: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            "Title: $title",
            Snackbar.LENGTH_LONG
        ).show()
    }
}
