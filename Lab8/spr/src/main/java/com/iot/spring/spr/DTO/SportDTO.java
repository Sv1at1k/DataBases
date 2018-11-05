package com.iot.spring.spr.DTO;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.iot.spring.spr.controller.SportsmanController;
import com.iot.spring.spr.domain.Sport;
import com.iot.spring.spr.exceptions.NoSuchSportException;
import com.iot.spring.spr.exceptions.NoSuchSportsmanException;


public class SportDTO extends ResourceSupport {
	private Sport sport;

	public SportDTO(Sport sport, Link selfLing) throws NoSuchSportException, NoSuchSportsmanException {
		this.sport = sport;
		add(selfLing);

	}

	public Integer getSportId() {
		return sport.getId();

	}

	public String getSportName() {
		return sport.getName();

	}
}
