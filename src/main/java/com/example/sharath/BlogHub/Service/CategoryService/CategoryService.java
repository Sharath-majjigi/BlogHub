package com.example.sharath.BlogHub.Service.CategoryService;


import com.example.sharath.BlogHub.DTOs.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO,Long cat_id);

    void deleteCategory(Long cat_id);

    CategoryDTO getCategoryById(Long cat_id);

    List<CategoryDTO> getAllCategory();
}
