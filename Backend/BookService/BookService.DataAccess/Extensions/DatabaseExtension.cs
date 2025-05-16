using System.Reflection;
using DbUp;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Npgsql;

namespace BookService.DataAccess.Extensions;

public static class DatabaseExtension
{
    public static IServiceCollection MigrateDatabase(this IServiceCollection services, IConfiguration configuration)
    {
        var connectionString = string.IsNullOrEmpty(configuration.GetSection("ConnectionStrings")["DefaultConnection"]) 
            ? configuration.GetConnectionString("DefaultConnection") 
            : configuration.GetSection("ConnectionStrings")["DefaultConnection"];
        
        if (string.IsNullOrEmpty(connectionString))
        {
            throw new Exception("Connection string is null or empty.");
        }

        // Ожидание готовности базы данных
        const int maxRetries = 5;
        const int delaySeconds = 5;
        bool isDbReady = false;

        for (int i = 0; i < maxRetries; i++)
        {
            try
            {
                using (var connection = new NpgsqlConnection(connectionString))
                {
                    connection.Open();
                    isDbReady = true;
                    Console.WriteLine("Successfully connected to the database.");
                    break;
                }
            }
            catch (NpgsqlException ex)
            {
                Console.WriteLine($"Failed to connect to the database: {ex.Message}. Retrying in {delaySeconds} seconds...");
                Thread.Sleep(delaySeconds * 1000);
            }
        }

        if (!isDbReady)
        {
            throw new Exception($"Could not connect to the database after {maxRetries} attempts.");
        }
        
        EnsureDatabase.For.PostgresqlDatabase(connectionString);

        var upgrader = DeployChanges.To
            .PostgresqlDatabase(connectionString)
            .WithScriptsEmbeddedInAssembly(Assembly.GetExecutingAssembly())
            .LogToConsole()
            .Build();

        upgrader.PerformUpgrade();

        return services;
    }
}