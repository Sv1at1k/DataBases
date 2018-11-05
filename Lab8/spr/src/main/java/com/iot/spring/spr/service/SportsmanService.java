package com.iot.spring.spr.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.spr.Repository.CategoryRepository;
import com.iot.spring.spr.Repository.SportRepository;
import com.iot.spring.spr.Repository.SportsmanRepository;
import com.iot.spring.spr.domain.Category;
import com.iot.spring.spr.domain.Sport;
import com.iot.spring.spr.domain.Sportsman;
import com.iot.spring.spr.exceptions.AlreadyExistsSportInSportsmanException;
import com.iot.spring.spr.exceptions.NoSuchCategoryException;
import com.iot.spring.spr.exceptions.NoSuchSportException;
import com.iot.spring.spr.exceptions.NoSuchSportsmanException;
import com.iot.spring.spr.exceptions.SportAbsentException;
import com.iot.spring.spr.exceptions.SportsmanHasNotSportException;


@Service
public class SportsmanService {
	@Autowired
	SportsmanRepository sportsmanRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	SportRepository sportRepository;
	
	
	public List<Sportsman> getSportsmanByCategoryId(Integer category_id) throws NoSuchCategoryException  {

      Category category = categoryRepository.findById(category_id).get();
      if (category == null) throw new NoSuchCategoryException();
      return category.getSportsman();
  }

  public Sportsman getSportsman(Integer sportsman_id) throws NoSuchSportsmanException  {

	  Sportsman sportsman = sportsmanRepository.findById(sportsman_id).get();
      if (sportsman == null) throw new NoSuchSportsmanException();
      return sportsman;
  }

  public List<Sportsman> getAllSportsmans() {
      return sportsmanRepository.findAll();
  }

 

  @Transactional
  public void createSportsman(Sportsman sportsman, Integer category_id) throws NoSuchCategoryException  {
      if (category_id > 0) {

          Category category = categoryRepository.findById(category_id).get();
          if (category == null) throw new NoSuchCategoryException();
          sportsman.setCategory(category);;
      }
      sportsmanRepository.save(sportsman);
  }

  @Transactional
  public void updateSportsman(Sportsman uSportsman, Integer spotsman_id, Integer category_id) throws NoSuchSportsmanException, NoSuchCategoryException  {
      
      Category category = categoryRepository.findById(category_id).get();
      if (category_id > 0) {
          if (category == null) throw new NoSuchCategoryException();
      }
   
      Sportsman sportsman = sportsmanRepository.findById(spotsman_id).get();
      if (sportsman == null) throw new NoSuchSportsmanException();
      
      sportsman.setName(uSportsman.getName());
    
      if (category_id > 0) sportsman.setCategory(category);
      else sportsman.setCategory(null);
      
     sportsmanRepository.save(sportsman);
  }

  @Transactional
  public void deleteSportsman(Integer person_id) throws Exception  {

      Sportsman sportsman = sportsmanRepository.findById(person_id).get();
      if (sportsman == null) throw new NoSuchSportsmanException();
     
      sportsmanRepository.delete(sportsman);
  }

  

  
}
