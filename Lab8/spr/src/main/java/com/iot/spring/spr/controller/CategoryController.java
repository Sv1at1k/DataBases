package com.iot.spring.spr.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iot.spring.spr.DTO.CategoryDTO;
import com.iot.spring.spr.domain.Category;
import com.iot.spring.spr.exceptions.NoSuchCategoryException;
import com.iot.spring.spr.exceptions.NoSuchSportException;
import com.iot.spring.spr.exceptions.NoSuchSportsmanException;
import com.iot.spring.spr.service.CategoryService;


@RestController
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/api/category")
	public ResponseEntity<List<CategoryDTO>> getAllCategory() throws NoSuchCategoryException, NoSuchSportsmanException, NoSuchSportException {
		List<Category> categoryList = categoryService.getAllCategory();
		Link link = linkTo(methodOn(CategoryController.class).getAllCategory()).withSelfRel();

		List<CategoryDTO> citiesDTO = new ArrayList<>();
		for (Category entity : categoryList) {
			Link selfLink = new Link(link.getHref() + "/" + entity.getID()).withSelfRel();
			CategoryDTO dto = new CategoryDTO(entity, selfLink);
			citiesDTO.add(dto);
		}

		return new ResponseEntity<>(citiesDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/api/category/{category_id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer category_id) throws NoSuchCategoryException, NoSuchSportsmanException, NoSuchSportException {
		Category category = categoryService.getCategory(category_id);
		Link link = linkTo(methodOn(CategoryController.class).getCategory(category_id)).withSelfRel();

		CategoryDTO categoryDTO = new CategoryDTO(category, link);

		return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/api/category/{category_id}")
	public ResponseEntity<CategoryDTO> addCategory(@RequestBody Category newCategory) throws NoSuchCategoryException, NoSuchSportsmanException, NoSuchSportException {
		categoryService.createCategory(newCategory);
		Link link = linkTo(methodOn(CategoryController.class).getCategory(newCategory.getID())).withSelfRel();

		CategoryDTO categoryDTO = new CategoryDTO(newCategory, link);

		return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);

	}
	@PutMapping(value = "/api/category/{category_id}")
	public ResponseEntity <CategoryDTO> updateCategory( @RequestBody Category uCategory,Integer category_id) throws NoSuchCategoryException, NoSuchSportsmanException, NoSuchSportException{
		categoryService.updateCategory(uCategory,category_id);
		Category category = categoryService.getCategory(category_id);
		Link link = linkTo(methodOn(CategoryController.class).getCategory(category_id)).withSelfRel();
		CategoryDTO categoryDTO = new CategoryDTO(category, link);

        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);

	}
	@DeleteMapping(value = "/api/category/{category_id}")
	public ResponseEntity deleteCategory(@PathVariable Integer category_id) throws Exception {
		categoryService.deleteCategory(category_id);
		
		return new ResponseEntity(HttpStatus.OK);
		
		
		
	} 
}
