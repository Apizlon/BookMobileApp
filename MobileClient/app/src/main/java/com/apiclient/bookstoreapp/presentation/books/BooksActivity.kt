package com.apiclient.bookstoreapp.presentation.books

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apiclient.bookstoreapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

// экран для отображения списка книг в RecyclerView
class BooksActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        recyclerView = findViewById(R.id.recyclerBooks)
        addButton = findViewById(R.id.btnAddBook)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BooksAdapter(emptyList()) // пока пустой

        addButton.setOnClickListener {
            startActivity(Intent(this, CrudBookActivity::class.java))
        }
    }
}

