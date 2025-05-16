using System.Text;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Npgsql;
using UserService.Api.Middlewares;
using UserService.Application.Interfaces;
using UserService.Application.Services;
using UserService.DataAccess;
using UserService.DataAccess.Interfaces;
using UserService.DataAccess.Repositories;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var connectionString = string.IsNullOrEmpty(builder.Configuration.GetSection("ConnectionStrings")["DefaultConnection"]) 
    ? builder.Configuration.GetConnectionString("DefaultConnection") 
    : builder.Configuration.GetSection("ConnectionStrings")["DefaultConnection"];
        
if (string.IsNullOrEmpty(connectionString))
{
    throw new Exception("Connection string is null or empty.");
}

builder.Services.AddDbContext<UserDbContext>(options =>
    options.UseNpgsql(connectionString));

builder.Services.AddScoped<IUserRepository, UserRepository>();
builder.Services.AddScoped<IUserService,UserService.Application.Services.UserService>();
ConfigureServices(builder.Services);
builder.Services.AddTransient<CustomExceptionHandlingMiddleware>();




var app = builder.Build();

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
using (var scope = app.Services.CreateScope())
{
    var dbContext = scope.ServiceProvider.GetRequiredService<UserDbContext>();
    try
    {
        dbContext.Database.Migrate(); // Применяем миграции
        Console.WriteLine("Database migrations applied successfully.");
    }
    catch (Exception ex)
    {
        Console.WriteLine($"Error applying migrations: {ex.Message}");
        throw;
    }
}

app.UseSwagger();
app.UseSwaggerUI();

app.UseMiddleware<CustomExceptionHandlingMiddleware>();
app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();

app.Run();


void ConfigureServices(IServiceCollection services)
{
    services
        .AddAuthentication(options =>
        {
            options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
            options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
        })
        .AddJwtBearer(cfg =>
        {
            cfg.RequireHttpsMetadata = true;
            cfg.SaveToken = true;
            cfg.TokenValidationParameters = new Microsoft.IdentityModel.Tokens.TokenValidationParameters()
            {
                IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes("placeholder-key-that-is-long-enough-for-sha256")),
                ValidateAudience = false,
                ValidateIssuer = false,
                ValidateLifetime = false,
                RequireExpirationTime = false,
                ClockSkew = TimeSpan.Zero,
                ValidateIssuerSigningKey = true
            };
        });

    services.AddScoped<ITokenBuilder, TokenBuilder>();
}