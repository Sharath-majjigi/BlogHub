package com.example.sharath.BlogHub.Controllers;

import com.example.sharath.BlogHub.DTOs.PostDto;
import com.example.sharath.BlogHub.DTOs.PostResponse;
import com.example.sharath.BlogHub.Exception.ResourceNotFoundException;
import com.example.sharath.BlogHub.Service.PostService.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostServiceImpl postService;


    @PostMapping("/user/{userid}/category/{catId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable("userid") Long user_id,
                                              @PathVariable("catId") Long cat_id){

        PostDto post;
        try{
            post=postService.createPost(postDto,cat_id,user_id);
        }
        catch(Exception e){
            throw new ResourceNotFoundException("Category/user","CatId/UserId",cat_id);
        }
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }



    @GetMapping("/user/{userid}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userid") Long user_id){
        List<PostDto>posts;
        try{
            posts=postService.getAllPostsByUser(user_id);
        }catch (Exception e){
            throw new ResourceNotFoundException("User","UserId",user_id);
        }
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }



    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("catId") Long cat_id){
        List<PostDto>posts;
        try{
            posts=postService.getAllPostsByCategory(cat_id);
        }catch (Exception e){
            throw new ResourceNotFoundException("Category","CategoryId",cat_id);
        }
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable("postId") Long post_id){
        PostDto updatedPost;
        try{
            updatedPost=postService.updatePost(postDto,post_id);
        }catch (Exception e){
            throw new ResourceNotFoundException("Post","PostId",post_id);
        }
        return new ResponseEntity<>(updatedPost,HttpStatus.ACCEPTED);
    }



    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") Long post_id){
        try{
            postService.deletePost(post_id);
        }catch (Exception e){
            throw new ResourceNotFoundException("Post","PostId",post_id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/allPosts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy){
        PostResponse allPosts;
        try{
            allPosts=postService.getAllPosts(pageNumber,pageSize,sortBy);
        }catch (Exception e){
            throw new ResourceNotFoundException("Post","PageNumber", (long) pageNumber);
        }
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }



    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("keyword") String keyword){
        List<PostDto> relatedPosts;
        try{
            relatedPosts=postService.searchPostsByTitle(keyword);
        }catch (Exception e){
            throw new ResourceNotFoundException("Post","With keyword NotFound", 0L);
        }
        return new ResponseEntity<>(relatedPosts,HttpStatus.OK);
    }
}
