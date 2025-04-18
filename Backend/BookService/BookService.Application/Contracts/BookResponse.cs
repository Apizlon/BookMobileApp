﻿namespace BookService.Application.Contracts;

public class BookResponse
{
    public int Id { get; set; }
    public string Name { get; set; }
    public string Description { get; set; }
    public string Author { get; set; }
}