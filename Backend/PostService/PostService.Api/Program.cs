using Microsoft.EntityFrameworkCore;
using PostService.Api.Middlewares;
using PostService.Application.Interfaces;
using PostService.DataAccess;
using PostService.DataAccess.Interfaces;
using PostService.DataAccess.Repositories;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<PostDbContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("DatabaseConnection")));

builder.Services.AddScoped<IPostRepository, PostRepository>();
builder.Services.AddScoped<IPostService,PostService.Application.Services.PostService>();
builder.Services.AddTransient<CustomExceptionHandlingMiddleware>();

var app = builder.Build();

// Configure the HTTP request pipeline.
app.UseSwagger();
app.UseSwaggerUI();

app.UseMiddleware<CustomExceptionHandlingMiddleware>();

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
