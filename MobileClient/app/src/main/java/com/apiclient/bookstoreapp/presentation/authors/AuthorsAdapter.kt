package com.apiclient.bookstoreapp.presentation.authors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apiclient.bookstoreapp.R
import com.apiclient.bookstoreapp.domain.model.Author

// адаптер для recyclerView (связывание данных об авторах с элементами списка)
class AuthorsAdapter(private val authors: List<Author>) :
    RecyclerView.Adapter<AuthorsAdapter.AuthorViewHolder>() {

    // отображение данных автора
    class AuthorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.tvAuthorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_author, parent, false)
        return AuthorViewHolder(view)
    }

    override fun getItemCount(): Int = authors.size

    // привязка данных автора к каждому элементу списка
    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = authors[position]
        holder.name.text = author.name

        // TODO: добавить редактирование
    }
}
