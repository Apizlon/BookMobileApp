namespace UserService.Core.Exceptions;

public abstract class NotFoundException(string message) : Exception(message);