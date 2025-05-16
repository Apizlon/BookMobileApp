package com.apiclient.bookstoreapp.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apiclient.bookstoreapp.data.repo.BookRepository
import com.apiclient.bookstoreapp.domain.model.BookRequest
import com.apiclient.bookstoreapp.domain.model.BookResponse
import kotlinx.coroutines.launch

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
                _books.postValue(bookList)
            } catch (e: Exception) {
                _error.postValue(e.message)
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
                } else {
                    repository.updateBook(id, bookRequest)
                }
                fetchBooks()
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun deleteBook(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteBook(id)
                fetchBooks()
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}