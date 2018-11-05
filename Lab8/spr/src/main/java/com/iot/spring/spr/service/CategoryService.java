package com.iot.spring.spr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.spr.Repository.CategoryRepository;
import com.iot.spring.spr.Repository.SportsmanRepository;
import com.iot.spring.spr.domain.Category;
import com.iot.spring.spr.exceptions.NoSuchCategoryException;


@Service
public class CategoryService {
	@Autowired
    CategoryRepository categoryRepository;
    private boolean ascending;

    @Autowired
    SportsmanRepository sportsmanRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer category_id) throws NoSuchCategoryException {
//        City city =cityRepository.findOne(city_id);//1.5.9
        Category category = categoryRepository.findById(category_id).get();//2.0.0.M7
        if (category == null) throw new NoSuchCategoryException();
        return category;
    }

    @Transactional
    public void createCategory(Category category) {
       categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(Category uCategory, Integer category_id) throws NoSuchCategoryException   {
       
        Category category = categoryRepository.findById(category_id).get();

        if (category == null) throw new NoSuchCategoryException();
 
        category.setName(uCategory.getName());
        categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Integer category_id) throws Exception  {
       
        Category category = categoryRepository.findById(category_id).get();
        if (category == null) throw new NoSuchCategoryException();
        if (category.getSportsman().size() != 0) throw new Exception();
        categoryRepository.delete(category);
    }
	
}
