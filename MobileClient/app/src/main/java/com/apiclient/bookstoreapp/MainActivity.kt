package com.apiclient.bookstoreapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Устанавливаем разметку из XML

        // Ищем элемент TextView по ID и обновляем текст
        val greetingText: TextView = findViewById(R.id.greetingText)
        greetingText.text = "Hello from MainActivity!"
    }
}
