﻿namespace BookService.Core.Models;

/// <summary>
/// Класс сущности Книга
/// </summary>
public class Book
{
    public int Id { get; set; }
    public string Name { get; set; }
    public string Description { get; set; }
    public string Author { get; set; }
}