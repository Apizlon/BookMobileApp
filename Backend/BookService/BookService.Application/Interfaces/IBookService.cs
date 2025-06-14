﻿using BookService.Application.Contracts;

namespace BookService.Application.Interfaces;

public interface IBookService
{
    Task<int> AddBookAsync(BookRequest bookRequest);
    Task DeleteBookAsync(int id);
    Task<List<BookResponse>> GetAllBooksAsync();
    Task<BookResponse> GetBookAsync(int id);
    Task UpdateBookAsync(int id, BookRequest bookRequest);
    Task<bool> BookExistsAsync(int id);
}