namespace UserService.Core.Exceptions;

public class UserNotFoundException : NotFoundException
{
    public UserNotFoundException(int id) : base($"Пользователь с id {id} не найден.")
    {
        
    }
}