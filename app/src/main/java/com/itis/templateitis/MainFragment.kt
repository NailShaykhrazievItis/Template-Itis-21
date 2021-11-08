package com.itis.templateitis

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.itis.templateitis.adapter.BookAdapter
import com.itis.templateitis.adapter.BookListAdapter
import com.itis.templateitis.header.BookWithHeaderAdapter
import com.itis.templateitis.model.Book
import com.itis.templateitis.model.BookRepository

class MainFragment : Fragment(R.layout.fragment_main) {

    private var bookAdapter: BookAdapter? = null
    private var bookTitleAdapter: BookWithHeaderAdapter? = null
    private var bookListAdapter: BookListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter(BookRepository.books, Glide.with(this)) {
            showSelectedTitle(it)
        }
        bookTitleAdapter = BookWithHeaderAdapter(BookRepository.booksWithHeader, Glide.with(this)) {
            showSelectedTitle(it)
        }
        bookListAdapter = BookListAdapter(Glide.with(this)) {
            showSelectedTitle(it)
        }

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val spacing = SpaceItemDecorator(requireContext())

        with(view) {
            findViewById<RecyclerView>(R.id.rv_books).run {
                adapter = bookListAdapter

                addItemDecoration(spacing)
                addItemDecoration(decorator)
            }
            findViewById<FloatingActionButton>(R.id.fab_refresh).setOnClickListener {
               refresh()
            }
        }

        bookListAdapter?.submitList(BookRepository.books)
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
        bookListAdapter?.submitList(BookRepository.books2) {
            view?.findViewById<RecyclerView>(R.id.rv_books)?.scrollToPosition(0)
        }

//        view?.findViewById<RecyclerView>(R.id.rv_books)?.scrollToPosition(0)
    }
}
