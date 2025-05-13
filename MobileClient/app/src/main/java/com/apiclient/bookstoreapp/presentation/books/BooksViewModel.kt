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

    fun fetchBooks() {
        viewModelScope.launch {
            try {
                val bookList = repository.getBooks()
                _books.postValue(bookList)
            } catch (e: Exception) {
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
                fetchBooks() // обновление списка после сохранения
            } catch (e: Exception) {
                // TODO: Обработать ошибку
            }
        }
    }
}