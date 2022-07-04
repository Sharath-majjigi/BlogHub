package com.example.sharath.BlogHub.Controllers;

import com.example.sharath.BlogHub.DTOs.ApiResponse;
import com.example.sharath.BlogHub.DTOs.CategoryDTO;
import com.example.sharath.BlogHub.Exception.ResourceNotFoundException;
import com.example.sharath.BlogHub.Service.CategoryService.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO cat;
        try{
            cat=categoryService.createCategory(categoryDTO);
        }catch (Exception e){
            throw new RuntimeException("Failed To Create");
        }
        return new ResponseEntity<>(cat, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable("id") Long cat_id){
        CategoryDTO cat;
        try{
            cat=categoryService.updateCategory(categoryDTO,cat_id);
        }catch (Exception e){
            throw new ResourceNotFoundException("Category","CategoryId",cat_id);
        }
        return new ResponseEntity<>(cat, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Long cat_id){
        try{
            categoryService.deleteCategory(cat_id);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Category", "CategoryId", cat_id);
        }
        return new ResponseEntity<>(new ApiResponse("Category is deleted Successfully!",true),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long cat_id){
        CategoryDTO categoryDTO;
        try{
           categoryDTO= categoryService.getCategoryById(cat_id);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Category", "CategoryId", cat_id);
        }
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryDTO> categories;
        try{
          categories=categoryService.getAllCategory();
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to Load Categories");
        }
        return new ResponseEntity<>(categories,HttpStatus.FOUND);
    }
}
