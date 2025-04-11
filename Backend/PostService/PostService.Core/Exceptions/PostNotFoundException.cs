namespace PostService.Core.Exceptions;

public class PostNotFoundException : NotFoundException
{
    public PostNotFoundException(int id) : base($"Пост с id {id} не найден.")
    {
        
    }
}