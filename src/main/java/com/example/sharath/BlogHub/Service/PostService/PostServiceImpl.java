package com.example.sharath.BlogHub.Service.PostService;

import com.example.sharath.BlogHub.DTOs.PostDto;
import com.example.sharath.BlogHub.DTOs.PostResponse;
import com.example.sharath.BlogHub.Models.Category;
import com.example.sharath.BlogHub.Models.Post;
import com.example.sharath.BlogHub.Models.User;
import com.example.sharath.BlogHub.Repository.CategoryDAO;
import com.example.sharath.BlogHub.Repository.PostDAO;
import com.example.sharath.BlogHub.Repository.UserDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostDAO postDAO;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserDAO userDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public PostDto createPost(PostDto postDto,Long cat_id,Long user_id) {
        Category postCategory=categoryDAO.findById(cat_id).get();
        User postUser=userDAO.findById(user_id).get();

        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setPostDate(new Date());
        post.setCategory(postCategory);
        post.setUser(postUser);

       Post savedPost =postDAO.save(post);
        return modelMapper.map(savedPost,PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Long post_id) {
        Post post=postDAO.findById(post_id).get();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setPostDate(postDto.getPostDate());
        post.setImageName(post.getImageName());
        post.setCategory(postDto.getCategory());
        post.setUser(postDto.getUser());

        Post updatedPost=postDAO.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Long post_id) {
        Post post=postDAO.findById(post_id).get();
        postDAO.delete(post);
    }

    @Override
    public PostDto getPostById(Long post_id) {
        Post post=postDAO.findById(post_id).get();
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Long cat_id) {
        Category category=categoryDAO.findById(cat_id).get();
        List<Post> posts = postDAO.findByCategory(category);
        List<PostDto> postDtos=posts.stream().map((post -> modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }


    public List<PostDto> getAllPostsByUser(Long user_id) {

        User user=userDAO.findById(user_id).get();
        List<Post> userPosts=postDAO.findByUser(user);
        List<PostDto> postDtos=userPosts.stream().map((post -> modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostResponse getAllPosts(int pageNum, int pageSize,String sortBy) {
        Pageable p= PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        Page<Post> pagePost=postDAO.findAll(p);
        List<Post> posts=pagePost.getContent();
        List<PostDto> postDtos=posts.stream().map((post -> modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> searchPostsByTitle(String keyword) {
        List<Post> posts=postDAO.findByTitleContaining(keyword);
        List<PostDto> postDtos=posts.stream().map((post -> modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }
}
