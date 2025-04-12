package com.apiclient.bookstoreapp.presentation.books

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.apiclient.bookstoreapp.R

class CrudBookActivity : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var authorEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_book)

        // TODO: добавить и поменять поля
        titleEditText = findViewById(R.id.etTitle)
        authorEditText = findViewById(R.id.etAuthor)
        saveButton = findViewById(R.id.btnSaveBook)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val author = authorEditText.text.toString()

            // TODO: добавить логику сохранения
            finish()
        }
    }
}
