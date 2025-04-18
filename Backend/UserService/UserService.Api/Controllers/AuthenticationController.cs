﻿using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using UserService.Application.Interfaces;
using UserService.Core.Models;
using UserService.DataAccess;

namespace UserService.Api.Controllers;

[Route("api/[controller]")]
[ApiController]
public class AuthenticationController : ControllerBase
{
    private readonly UserDbContext _context;
    private readonly ITokenBuilder _tokenBuilder;

    public AuthenticationController(
        UserDbContext context,
        ITokenBuilder tokenBuilder)
    {
        _context = context;
        _tokenBuilder = tokenBuilder;
    }
    
    [HttpPost("login")]
    public async Task<IActionResult> Login([FromBody]User user)
    {
        var dbUser = await _context
            .Users
            .SingleOrDefaultAsync(u => u.Username == user.Username);

        if (dbUser == null)
        {
            return NotFound("User not found.");
        }

        // This is just an example, made for simplicity; do not store plain passwords in the database
        // Always hash and salt your passwords
        var isValid = dbUser.Password == user.Password;

        if (!isValid)
        {
            return BadRequest("Could not authenticate user.");
        }

        var token = _tokenBuilder.BuildToken(user.Username);

        return Ok(token);
    }
    
    [HttpGet("verify")]
    [Authorize(AuthenticationSchemes = JwtBearerDefaults.AuthenticationScheme)]
    public async Task<IActionResult> VerifyToken()
    {
        var username = User
            .Claims
            .SingleOrDefault();

        if (username == null)
        {
            return Unauthorized();
        }

        var userExists = await _context
            .Users
            .AnyAsync(u => u.Username == username.Value);

        if (!userExists)
        {
            return Unauthorized();
        }

        return NoContent();
    }
}