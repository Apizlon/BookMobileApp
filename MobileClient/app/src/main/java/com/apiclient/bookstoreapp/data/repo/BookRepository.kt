package com.apiclient.bookstoreapp.data.repo

import com.apiclient.bookstoreapp.data.api.RetrofitClient
import com.apiclient.bookstoreapp.domain.model.BookRequest
import com.apiclient.bookstoreapp.domain.model.BookResponse
import retrofit2.HttpException
import java.io.IOException

class BookRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun getBooks(): List<BookResponse> {
        return try {
            apiService.getBooks()
        } catch (e: HttpException) {
            throw Exception("HTTP error: ${e.code()} - ${e.message()}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        }
    }

    suspend fun createBook(book: BookRequest): BookResponse {
        return try {
            apiService.createBook(book)
        } catch (e: HttpException) {
            throw Exception("HTTP error: ${e.code()} - ${e.message()}")
        } catch (e: IOException) {
            throw Exception("Network error: ${e.message}")
        }
    }

    suspend fun updateBook(id: Long, book: BookRequest): BookResponse {
        return try {
            apiService.updateBook(id, book)
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