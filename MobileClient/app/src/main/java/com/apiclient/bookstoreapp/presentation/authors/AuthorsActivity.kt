package com.apiclient.bookstoreapp.presentation.authors

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apiclient.bookstoreapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

// экран для отображения списка авторов в RecyclerView
class AuthorsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authors)

        recyclerView = findViewById(R.id.recyclerAuthors)
        addButton = findViewById(R.id.btnAddAuthor)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AuthorsAdapter(emptyList()) // пока пустой

        addButton.setOnClickListener {
            startActivity(Intent(this, CrudAuthorActivity::class.java))
        }
    }
}
