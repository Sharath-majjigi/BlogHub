package com.example.sharath.BlogHub.Repository;

import com.example.sharath.BlogHub.DTOs.PostDto;
import com.example.sharath.BlogHub.Models.Category;
import com.example.sharath.BlogHub.Models.Post;
import com.example.sharath.BlogHub.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostDAO extends JpaRepository<Post,Long> {

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    List<Post> findByTitleContaining(String title);
}
