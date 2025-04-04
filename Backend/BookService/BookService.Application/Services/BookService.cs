using BookService.Application.Contracts;
using BookService.Application.Interfaces;

using BookService.Application.Mappers;
using BookService.Application.Validators;
using BookService.Core.Exceptions;
using BookService.DataAccess.Interfaces;

namespace BookService.Application.Services;

public class BookService : IBookService
{
    private readonly IBookRepository _bookRepository;
    public BookService(IBookRepository bookRepository)
    {
        _bookRepository = bookRepository;
    }
    
    public async Task<int> AddBookAsync(Contracts.BookRequest bookRequest)
    {
        bookRequest.AddValidation();
        return await _bookRepository.AddBookAsync(bookRequest.MapToCore());
    }

    public async Task DeleteBookAsync(int id)
    {
        var isBookExists = await BookExistsAsync(id);
        if (!isBookExists)
        {
            throw new BookNotFoundException(id);
        }
        await _bookRepository.DeleteBookAsync(id);
    }

    public async Task<BookResponse> GetBookAsync(int id)
    {
        var isBookExists = await BookExistsAsync(id);
        if (!isBookExists)
        {
            throw new BookNotFoundException(id);
        }
        var book = await _bookRepository.GetBookAsync(id);
        return book.MapToContract();
    }

    public async Task UpdateBookAsync(int id, Contracts.BookRequest bookRequest)
    {
        var isBookExists = await BookExistsAsync(id);
        bookRequest.UpdateValidation(id,isBookExists);
        await _bookRepository.UpdateBookAsync(id, bookRequest.MapToCore());
    }

    public async Task<bool> BookExistsAsync(int id)
    {
        return await _bookRepository.BookExistsAsync(id);
    }
}