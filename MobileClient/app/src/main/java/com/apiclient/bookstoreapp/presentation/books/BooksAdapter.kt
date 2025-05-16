package com.apiclient.bookstoreapp.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.domain.model.BookResponse

class BooksAdapter(
    private var books: MutableList<BookResponse>,
    private val onMenuClick: (BookResponse) -> Unit
) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvBookName)
        val description: TextView = view.findViewById(R.id.tvBookDescription)
        val author: TextView = view.findViewById(R.id.tvBookAuthor)
        val menuIcon: ImageView = view.findViewById(R.id.ivMenu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.name.text = book.name
        holder.description.text = book.description
        holder.author.text = book.author
        holder.menuIcon.setOnClickListener {
            onMenuClick(book)
        }
    }

    override fun getItemCount(): Int = books.size

    fun updateBooks(newBooks: List<BookResponse>) {
        val diffCallback = BookDiffCallback(books, newBooks)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        books.clear()
        books.addAll(newBooks)
        diffResult.dispatchUpdatesTo(this)
    }

    private class BookDiffCallback(
        private val oldList: List<BookResponse>,
        private val newList: List<BookResponse>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}