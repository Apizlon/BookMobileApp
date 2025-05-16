package com.apiclient.bookstoreapp.presentation.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apiclient.bookstoreapp.data.repo.BookRepository
import com.apiclient.bookstoreapp.domain.model.Book
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    private val repository = BookRepository()
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books
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

    fun saveBook(book: Book) {
        viewModelScope.launch {
            try {
                if (book.id == 0L) {
                    repository.createBook(book)
                } else {
                    repository.updateBook(book)
                }
                fetchBooks() // Обновление списка после сохранения
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun deleteBook(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteBook(id)
                fetchBooks() // Обновление списка после удаления
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}