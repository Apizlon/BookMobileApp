using BookService.DataAccess.Interfaces;
using BookService.DataAccess.Repositories;
using Microsoft.Extensions.DependencyInjection;

namespace BookService.DataAccess.Extensions;

public static class RepositoriesExtension
{
    public static IServiceCollection AddRepositories(this IServiceCollection services)
    {
        return services
            .AddScoped<IBookRepository, BookRepository>();
    }
}