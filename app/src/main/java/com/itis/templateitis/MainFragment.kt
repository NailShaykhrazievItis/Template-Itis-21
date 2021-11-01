package com.itis.templateitis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.itis.templateitis.header.BookWithHeaderAdapter

class MainFragment : Fragment(R.layout.fragment_main) {

    private var bookAdapter: BookAdapter? = null
    private var bookTitleAdapter: BookWithHeaderAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter(BookRepository.books, Glide.with(this)) {
            showSelectedTitle(it)
        }
        bookTitleAdapter = BookWithHeaderAdapter(BookRepository.booksWithHeader, Glide.with(this)) {
            showSelectedTitle(it)
        }

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(view) {
            findViewById<RecyclerView>(R.id.rv_books).run {
                adapter = bookTitleAdapter

                addItemDecoration(spacing)
                addItemDecoration(decorator)
            }
            findViewById<FloatingActionButton>(R.id.fab_refresh).setOnClickListener {
                updateOneItem()
            }
        }
    }

    private fun showSelectedTitle(title: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            "Title: $title",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun updateOneItem() {
        bookAdapter?.updateCurrentItem(
            Book(
                "TEST",
                "Маргарита",
                "https://flysmartavia.com/media/images/city/20200707_kaz.jpg"
            ), 0
        )
    }

    private fun refresh() {
        bookAdapter?.updateData(
            arrayListOf(
                Book(
                    "Test",
                    "Author",
                    "https://www.tourprom.ru/site_media/cache/22/91/22913546eb2587bbb353d4c166e41c70.jpg"
                )
            )
        )
    }
}
