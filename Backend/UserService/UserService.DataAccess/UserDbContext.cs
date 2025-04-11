using Microsoft.EntityFrameworkCore;
using UserService.Core.Models;

namespace UserService.DataAccess;

public class UserDbContext : DbContext
{
    public UserDbContext(DbContextOptions<UserDbContext> options) : base(options) { }

    public DbSet<User> Users { get; set; }
}