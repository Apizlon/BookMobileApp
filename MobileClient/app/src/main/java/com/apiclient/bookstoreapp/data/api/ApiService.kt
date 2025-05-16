package com.apiclient.bookstoreapp.data.api

import com.apiclient.bookstoreapp.domain.model.Book
import retrofit2.http.*

interface ApiService {
    @GET("Book")
    suspend fun getBooks(): List<Book>

    @POST("Book")
    suspend fun createBook(@Body book: Book): Book

    @PUT("Book/{id}")
    suspend fun updateBook(@Path("id") id: Long, @Body book: Book): Book

    @DELETE("Book/{id}")
    suspend fun deleteBook(@Path("id") id: Long)
}