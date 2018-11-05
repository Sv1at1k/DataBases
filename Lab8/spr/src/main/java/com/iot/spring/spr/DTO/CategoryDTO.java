package com.iot.spring.spr.DTO;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.iot.spring.spr.controller.CategoryController;
import com.iot.spring.spr.controller.SportsmanController;
import com.iot.spring.spr.domain.Category;
import com.iot.spring.spr.exceptions.NoSuchCategoryException;
import com.iot.spring.spr.exceptions.NoSuchSportException;
import com.iot.spring.spr.exceptions.NoSuchSportsmanException;


public class CategoryDTO extends ResourceSupport {
	Category category;
	public CategoryDTO(Category category,Link selfLink) throws NoSuchCategoryException, NoSuchSportsmanException, NoSuchSportException {
		this.category = category;
		add(selfLink);
		add(linkTo(methodOn(SportsmanController.class).getSportsamnByCategoryID(category.getID())).withRel("sportsmans"));

	}
	public Integer getCategoryId() {
		return category.getID();
		
	}
	public String getName() {
		return category.getName();
		
	}
	

}
