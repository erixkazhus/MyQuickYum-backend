package com.online.service;

import com.online.model.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoryService {
    public Category createCategory(String name, Long userId) throws Exception;
   public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

   public Category findCategoryById(Long id) throws Exception;

}
