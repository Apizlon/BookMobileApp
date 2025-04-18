using PostService.Application.Contracts;

namespace PostService.Application.Interfaces;

public interface IPostService
{
    Task<int> AddPostAsync(PostRequest postRequest);
    Task DeletePostAsync(int id);
    Task<PostResponse> GetPostAsync(int id);
    Task<IEnumerable<PostResponse>> GetPostsAsync();
    Task UpdatePostAsync(int id, PostRequest postRequest);
    Task<bool> PostExistsAsync(int id);
}