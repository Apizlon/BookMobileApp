using BookService.Core.Models;

namespace BookService.DataAccess.Interfaces;

public interface IBookRepository
{
    Task<int> AddBookAsync(Book book);
    Task DeleteBookAsync(int id);
    Task<List<Book>> GetAllBooksAsync();
    Task<Book> GetBookAsync(int id);
    Task UpdateBookAsync(int id,Book book);
    Task<bool> BookExistsAsync(int id);
}