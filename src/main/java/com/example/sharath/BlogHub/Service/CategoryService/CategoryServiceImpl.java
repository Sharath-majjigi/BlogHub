package com.example.sharath.BlogHub.Service.CategoryService;

import com.example.sharath.BlogHub.DTOs.CategoryDTO;
import com.example.sharath.BlogHub.Models.Category;
import com.example.sharath.BlogHub.Repository.CategoryDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO,Category.class);
        Category savedCategory = categoryDAO.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long cat_id) {
        Category category = categoryDAO.findById(cat_id).get();
        category.setId(categoryDTO.getId());
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long cat_id) {
        Category category = categoryDAO.findById(cat_id).get();
        categoryDAO.delete(category);
    }

    @Override
    public CategoryDTO getCategoryById(Long cat_id) {
        Category category = categoryDAO.findById(cat_id).get();
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories=categoryDAO.findAll();
        List<CategoryDTO> categoryDtos= categories.stream().map((category -> modelMapper.map(category,CategoryDTO.class))).collect(Collectors.toList());
        return categoryDtos;
    }
}
