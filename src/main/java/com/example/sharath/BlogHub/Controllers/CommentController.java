package com.example.sharath.BlogHub.Controllers;

import com.example.sharath.BlogHub.DTOs.CommentDto;
import com.example.sharath.BlogHub.Service.Comment.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @PreAuthorize("hasRole('NORMAL_USER')")
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId){
        CommentDto comment;
        comment=commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('NORMAL_USER')")
    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long comment_id){
        commentService.deleteComment(comment_id);
        return new ResponseEntity<>("Comment deleted Successfully",HttpStatus.OK);
    }
}
