﻿using UserService.Application.Contracts;
using UserService.Core.Models;

namespace UserService.Application.Mappers;

public static class UserMapper
{
    public static User MapToCore(this UserRequest request)
    {
        return new User
        {
            Username = request.Username,
            Password = request.Password
        };
    }

    public static UserResponse MapToContract(this User user)
    {
        return new UserResponse
        {
            Id = user.Id,
            Username = user.Username,
            Password = user.Password
        };
    }

    public static IEnumerable<UserResponse> MapToContract(this IEnumerable<User> users)
    {
        return users.Select(x => x.MapToContract());
    }
}