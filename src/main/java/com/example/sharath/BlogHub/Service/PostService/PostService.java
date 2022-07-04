package com.example.sharath.BlogHub.Service.PostService;

import com.example.sharath.BlogHub.DTOs.PostDto;
import com.example.sharath.BlogHub.DTOs.PostResponse;
import com.example.sharath.BlogHub.Models.Post;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto,Long cat_id,Long user_id);

    //Update post
    PostDto updatePost(PostDto postDto,Long post_id);

    //Delete Post
    void deletePost(Long post_id);

    //Get post by Id
    PostDto getPostById(Long post_id);

    //Get All Posts By Category
    List<PostDto> getAllPostsByCategory(Long cat_id);

    //Get All Posts By User
    List<PostDto> getAllPostsByUser(Long user_id);

    //Get All Posts
    PostResponse getAllPosts(int pageNum, int pageSize,String sortBy);

    //Search Posts
    List<PostDto> searchPostsByTitle(String title);
}
