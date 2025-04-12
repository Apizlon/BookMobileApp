namespace UserService.Application.Interfaces;

public interface ITokenBuilder
{
    string BuildToken(string username);
}