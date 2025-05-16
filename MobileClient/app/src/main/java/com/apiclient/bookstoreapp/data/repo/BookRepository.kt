package com.apiclient.bookstoreapp.data.repo

import com.apiclient.bookstoreapp.data.api.RetrofitClient
import com.apiclient.bookstoreapp.domain.model.Book
import retrofit2.HttpException
import java.io.IOException

class BookRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun getBooks(): List<Book> {
        return try {
            apiService.getBooks()
        } catch (e: HttpException) {
            // Логирование HTTP-ошибок (например, 404, 500)
            throw Exception("HTTP error: ${e.code()} - ${e.message()}")
        } catch (e: IOException) {
            // Логирование сетевых ошибок (например, нет интернета)
            throw Exception("Network error: ${e.message}")
        }
    }

    suspend fun createBook(book: Book): Book {
        return try {
            apiService.createBook(book)
        } catch (e: HttpException) {
            throw Exception("HTTP error: ${e.code()} - ${e.message()}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        }
    }

    suspend fun updateBook(book: Book): Book {
        return try {
            apiService.updateBook(book.id, book)
        } catch (e: HttpException) {
            throw Exception("HTTP error: ${e.code()} - ${e.message()}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        }
    }

    suspend fun deleteBook(id: Long) {
        try {
            apiService.deleteBook(id)
        } catch (e: HttpException) {
            throw Exception("HTTP error: ${e.code()} - ${e.message()}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        }
    }
}