package com.apiclient.bookstoreapp.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.domain.model.BookResponse

class BooksAdapter(
    private var books: List<BookResponse>,
    private val onBookClick: (BookResponse) -> Unit
) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvBookName)
        val description: TextView = view.findViewById(R.id.tvBookDescription)
        val author: TextView = view.findViewById(R.id.tvBookAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.name.text = book.name
        holder.author.text = book.author
        holder.description.text = book.description
        holder.itemView.setOnClickListener {
            onBookClick(book)
        }
    }

    override fun getItemCount(): Int = books.size

    fun updateBooks(newBooks: List<BookResponse>) {
        books = newBooks
        notifyDataSetChanged()
    }
}