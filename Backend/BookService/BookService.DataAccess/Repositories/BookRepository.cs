using System.Data.Common;
using BookService.Core.Models;
using BookService.DataAccess.Interfaces;
using BookService.DataAccess.SqlScripts;
using Dapper;
using Microsoft.Extensions.Configuration;
using Npgsql;

namespace BookService.DataAccess.Repositories;

public class BookRepository : IBookRepository
{
    private readonly string _dbConnection;
    public BookRepository(IConfiguration configuration)
    {
        var connectionString = string.IsNullOrEmpty(configuration.GetSection("ConnectionStrings")["DefaultConnection"]) 
            ? configuration.GetConnectionString("DefaultConnection") 
            : configuration.GetSection("ConnectionStrings")["DefaultConnection"];
        
        if (string.IsNullOrEmpty(connectionString))
        {
            throw new Exception("Connection string is null or empty.");
        }
        _dbConnection = connectionString;
    }
    
    private async Task<DbConnection> CreateConnectionAsync()
    {
        var connection = new NpgsqlConnection(_dbConnection);
        await connection.OpenAsync();
        return connection;
    }

    public async Task<int> AddBookAsync(Book book)
    {
        await using var connection = await CreateConnectionAsync();
        var id = await connection.ExecuteScalarAsync<int>(Sql.AddBook, book);

        return id;
    }

    public async Task DeleteBookAsync(int id)
    {
        await using var connection = await CreateConnectionAsync();
        await connection.ExecuteAsync(Sql.DeleteBook, new { Id = id });
    }

    public async Task<List<Book>> GetAllBooksAsync()
    {
        await using var connection = await CreateConnectionAsync();
        var books = await connection.QueryAsync<Book>(@"SELECT ""Id"", ""Name"", ""Description"", ""Author"" FROM ""Books"";",new {});
        return books.ToList();
    }
    
    public async Task<Book> GetBookAsync(int id)
    {
        await using var connection = await CreateConnectionAsync();
        var book = await connection.QuerySingleOrDefaultAsync<Book>(Sql.GetBook, new { Id = id });

        return book;
    }

    public async Task UpdateBookAsync(int id, Book book)
    {
        await using var connection = await CreateConnectionAsync();
        await connection.ExecuteAsync(Sql.UpdateBook, new { Id = id, book.Name,book.Description,book.Author});
    }

    public async Task<bool> BookExistsAsync(int id)
    {
        await using var connection = await CreateConnectionAsync();
        var exists = await connection.ExecuteScalarAsync<bool>(Sql.ExistsBook, new { Id = id });

        return exists;
    }
}