package com.apiclient.bookstoreapp.presentation.authors

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.apiclient.bookstoreapp.R

class CrudAuthorActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_author)

        // TODO: добавить и поменять поля
        nameEditText = findViewById(R.id.etAuthorName)
        saveButton = findViewById(R.id.btnSaveAuthor)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()

            // TODO: добавить логику сохранения
            finish()
        }
    }
}
