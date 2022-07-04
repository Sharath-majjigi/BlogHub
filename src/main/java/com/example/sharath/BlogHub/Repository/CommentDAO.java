package com.example.sharath.BlogHub.Repository;

import com.example.sharath.BlogHub.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment,Long> {
}
