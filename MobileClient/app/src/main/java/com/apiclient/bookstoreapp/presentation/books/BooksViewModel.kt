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

    // используем корутины
    fun fetchBooks() {
        viewModelScope.launch { // launch создается корутину для выполнения get запроса через getBooks
            try {
                val bookList = repository.getBooks()
                Log.d("BooksViewModel", "Fetched ${bookList.size} books: ${bookList.map { it.id }}")

                 // postValue используется для обновления LiveData в основном потоке
                _books.postValue(bookList.toList()) // Создаём новый список
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Error fetching books: ${e.message}", e)
                _error.postValue("Не удалось загрузить книги: ${e.message}")
                _books.postValue(emptyList())
            }
        }
    }

    fun saveBook(name: String, description: String, author: String, id: Int = 0) {
        viewModelScope.launch {
            try {
                val bookRequest = BookRequest(name, description, author)
                if (id == 0) {
                    val newBookId = repository.createBook(bookRequest)
                    Log.d("BooksViewModel", "Created book: $name with ID: $newBookId")
                } else {
                    repository.updateBook(id, bookRequest)
                    Log.d("BooksViewModel", "Updated book: $id")
                }
                fetchBooks()
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Error saving book: ${e.message}", e)
                _error.postValue("Не удалось сохранить книгу: ${e.message}")
            }
        }
    }

    fun deleteBook(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteBook(id)
                Log.d("BooksViewModel", "Deleted book: $id")
                fetchBooks()
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Error deleting book: ${e.message}", e)
                _error.postValue("Не удалось удалить книгу: ${e.message}")
            }
        }
    }
}