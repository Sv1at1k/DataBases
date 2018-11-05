package com.iot.spring.spr.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.iot.spring.spr.DTO.SportDTO;
import com.iot.spring.spr.domain.Sport;
import com.iot.spring.spr.exceptions.ExistsSportsmanForSportException;
import com.iot.spring.spr.exceptions.NoSuchSportException;
import com.iot.spring.spr.exceptions.NoSuchSportsmanException;
import com.iot.spring.spr.service.SportService;

@RestController
public class SportContoller {
	@Autowired
	SportService sportService;

	@GetMapping(value = "/api/sport/{sport_id}")
	public ResponseEntity<SportDTO> getSport(@PathVariable Integer sport_id)
			throws NoSuchSportException, NoSuchSportsmanException {
		Sport sport = sportService.getSport(sport_id);
		Link link = linkTo(methodOn(SportContoller.class).getSport(sport_id)).withSelfRel();

		SportDTO sportDTO = new SportDTO(sport, link);

		return new ResponseEntity<>(sportDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/api/sport")
	public ResponseEntity<List<SportDTO>> getAllSports() throws NoSuchSportException, NoSuchSportsmanException {
		List<Sport> sportList = sportService.getAllSport();
		Link link = linkTo(methodOn(SportContoller.class).getAllSports()).withSelfRel();

		List<SportDTO> sportDTO = new ArrayList<>();
		for (Sport entity : sportList) {
			Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
			SportDTO dto = new SportDTO(entity, selfLink);
			sportDTO.add(dto);
		}

		return new ResponseEntity<>(sportDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/api/sport")
	public ResponseEntity<SportDTO> addSport(@RequestBody Sport newSport)
			throws NoSuchSportException, NoSuchSportsmanException {
		sportService.createSport(newSport);
		Link link = linkTo(methodOn(SportContoller.class).getSport(newSport.getId())).withSelfRel();

		SportDTO sportDTO = new SportDTO(newSport, link);

		return new ResponseEntity<>(sportDTO, HttpStatus.CREATED);
	}

	@PutMapping(value = "/api/sport/{sport_id}")
	public ResponseEntity<SportDTO> updateBook(@RequestBody Sport uSport, @PathVariable Integer sport_id)
			throws NoSuchSportException, NoSuchSportsmanException {
		sportService.updateSport(uSport, sport_id);
		Sport sport = sportService.getSport(sport_id);
		Link link = linkTo(methodOn(SportContoller.class).getSport(sport_id)).withSelfRel();

		SportDTO sportDTO = new SportDTO(sport, link);

		return new ResponseEntity<>(sportDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/sport/{sport_id}")
	public ResponseEntity deleteSport(@PathVariable Integer sport_id)
			throws NoSuchSportException, ExistsSportsmanForSportException {
		sportService.deleteSport(sport_id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
