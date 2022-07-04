package com.example.sharath.BlogHub.Service.Comment;

import com.example.sharath.BlogHub.Exception.ResourceNotFoundException;
import  com.example.sharath.BlogHub.Models.Comment;
import com.example.sharath.BlogHub.DTOs.CommentDto;
import com.example.sharath.BlogHub.Models.Post;
import com.example.sharath.BlogHub.Repository.CommentDAO;
import com.example.sharath.BlogHub.Repository.PostDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostDAO postDAO;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long post_id) {
        Comment comment,savedComment;
        try{
             Post post = postDAO.findById(post_id).get();
             comment = modelMapper.map(commentDto, Comment.class);
             comment.setPost(post);
             savedComment=commentDAO.save(comment);
             return modelMapper.map(comment,CommentDto.class);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Post","PostId",post_id);
        }

    }

    @Override
    public void deleteComment(Long comment_id) {
       Comment comment;
        try{
            comment=commentDAO.findById(comment_id).get();
            commentDAO.delete(comment);
        }catch(Exception e){
            throw new ResourceNotFoundException("Comment","CommentId",comment_id);
        }
    }
}
