using BookService.Application.Interfaces;
using Microsoft.Extensions.DependencyInjection;

namespace BookService.Application.Extensions;

public static class ServicesExtension
{
    public static IServiceCollection AddServices(this IServiceCollection services)
    {
        return services
            .AddScoped<IBookService,Services.BookService>();
    }
}