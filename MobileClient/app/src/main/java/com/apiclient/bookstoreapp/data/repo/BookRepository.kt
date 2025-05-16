package com.apiclient.bookstoreapp.data.repo

import com.apiclient.bookstoreapp.data.api.RetrofitClient
import com.apiclient.bookstoreapp.domain.model.Book

class BookRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun getBooks(): List<Book> {
        return apiService.getBooks()
    }

    suspend fun createBook(book: Book): Book {
        return apiService.createBook(book)
    }

    suspend fun updateBook(book: Book): Book {
        return apiService.updateBook(book.id, book)
    }

    suspend fun deleteBook(id: Long) {
        apiService.deleteBook(id)
    }
}
