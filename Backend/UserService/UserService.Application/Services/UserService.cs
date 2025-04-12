using UserService.Application.Contracts;
using UserService.Application.Interfaces;
using UserService.Application.Mappers;
using UserService.Core.Exceptions;
using UserService.DataAccess.Interfaces;

namespace UserService.Application.Services;

public class UserService : IUserService
{
    private readonly IUserRepository _userRepository;
    public UserService(IUserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    public async Task<int> AddUserAsync(UserRequest userRequest)
    {
        return await _userRepository.AddUserAsync(userRequest.MapToCore());
    }

    public async Task DeleteUserAsync(int id)
    {
        var isUserExists = await UserExistsAsync(id);
        if (!isUserExists)
        {
            throw new UserNotFoundException(id);
        }
        await _userRepository.DeleteUserAsync(id);
    }

    public async Task<UserResponse> GetUserAsync(int id)
    {
        var isUserExists = await UserExistsAsync(id);
        if (!isUserExists)
        {
            throw new UserNotFoundException(id);
        }
        var user = await _userRepository.GetUserAsync(id);
        return user.MapToContract();
    }

    public async Task UpdateUserAsync(int id, UserRequest userRequest)
    {
        var isUserExists = await UserExistsAsync(id);
        if (!isUserExists)
        {
            throw new UserNotFoundException(id);
        }
        await _userRepository.UpdateUserAsync(id, userRequest.MapToCore());
    }

    public async Task<bool> UserExistsAsync(int id)
    {
        return await _userRepository.UserExistsAsync(id);
    }
}