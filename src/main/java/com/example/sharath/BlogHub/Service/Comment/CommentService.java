package com.example.sharath.BlogHub.Service.Comment;

import com.example.sharath.BlogHub.DTOs.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Long post_id);

    void deleteComment(Long comment_id);

}
