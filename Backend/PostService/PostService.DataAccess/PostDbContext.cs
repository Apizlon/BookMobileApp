using Microsoft.EntityFrameworkCore;
using PostService.Core.Models;

namespace PostService.DataAccess;

public class PostDbContext : DbContext
{
    public PostDbContext(DbContextOptions<PostDbContext> options) : base(options) { }

    public DbSet<Post> Posts { get; set; }
}