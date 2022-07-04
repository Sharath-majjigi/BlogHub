package com.example.sharath.BlogHub.Repository;

import com.example.sharath.BlogHub.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<Category,Long> {
}
