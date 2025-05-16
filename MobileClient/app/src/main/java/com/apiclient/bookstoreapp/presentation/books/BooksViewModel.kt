package com.apiclient.bookstoreapp.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apiclient.bookstoreapp.data.repo.BookRepository
import com.apiclient.bookstoreapp.domain.model.BookRequest
import com.apiclient.bookstoreapp.domain.model.BookResponse
import kotlinx.coroutines.launch
import android.util.Log

class BooksViewModel : ViewModel() {

    private val repository = BookRepository()
    private val _books = MutableLiveData<List<BookResponse>>()
    val books: LiveData<List<BookResponse>> get() = _books
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchBooks() {
        viewModelScope.launch {
            try {
                val bookList = repository.getBooks()
                Log.d("BooksViewModel", "Fetched ${bookList.size} books: ${bookList.map { it.id }}")
                _books.postValue(bookList)
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Error fetching books: ${e.message}", e)
                _error.postValue("Failed to fetch books: ${e.message}")
                _books.postValue(emptyList())
            }
        }
    }

    fun saveBook(name: String, description: String, author: String, id: Long = 0L) {
        viewModelScope.launch {
            try {
                val bookRequest = BookRequest(name, description, author)
                if (id == 0L) {
                    repository.createBook(bookRequest)
                    Log.d("BooksViewModel", "Created book: $name")
                } else {
                    repository.updateBook(id, bookRequest)
                    Log.d("BooksViewModel", "Updated book: $id")
                }
                fetchBooks()
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Error saving book: ${e.message}", e)
                _error.postValue("Failed to save book: ${e.message}")
            }
        }
    }

    fun deleteBook(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteBook(id)
                Log.d("BooksViewModel", "Deleted book: $id")
                fetchBooks()
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Error deleting book: ${e.message}", e)
                _error.postValue("Failed to delete book: ${e.message}")
            }
        }
    }
}