package com.apiclient.bookstoreapp.data.api

import com.apiclient.bookstoreapp.domain.model.BookRequest
import com.apiclient.bookstoreapp.domain.model.BookResponse
import retrofit2.http.*

interface ApiService {
    @GET("Book")
    suspend fun getBooks(): List<BookResponse>

    @POST("Book")
    suspend fun createBook(@Body book: BookRequest): BookResponse

    @PATCH("Book/{id}")
    suspend fun updateBook(@Path("id") id: Long, @Body book: BookRequest): BookResponse

    @DELETE("Book/{id}")
    suspend fun deleteBook(@Path("id") id: Long)
}