package com.apiclient.bookstoreapp.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.domain.model.Book

// адаптер для recyclerView (связывание данных о книгах с элементами списка)
class BooksAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    // отображение данных книги
    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tvBookTitle)
        val author = view.findViewById<TextView>(R.id.tvBookAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = books.size

    // привязка данных книги к каждому элементу списка
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.title.text = book.title
        holder.author.text = book.authorName

        // TODO: добавить setOnClickListener для редактирования
    }
}
