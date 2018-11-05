package com.iot.spring.spr.DTO;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.iot.spring.spr.controller.SportContoller;
import com.iot.spring.spr.domain.Sportsman;
import com.iot.spring.spr.exceptions.NoSuchSportException;
import com.iot.spring.spr.exceptions.NoSuchSportsmanException;


public class SportsmanDTO extends ResourceSupport  {
			private Sportsman sportsman;

			public SportsmanDTO(Sportsman sportsman,Link selflink) throws NoSuchSportsmanException, NoSuchSportException {
				this.sportsman = sportsman;
				add(selflink);

				
			}
			
			public Integer getSportsmanId() {
				return sportsman.getId();
			}

			public String getSportsmanName() {
				return sportsman.getName();
			}
			public Integer getSportsmanCategoryId() {
				return sportsman.getCategory().getID();
			}

			
	
			
}
