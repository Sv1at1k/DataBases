package com.iot.spring.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iot.spring.spr.DTO.SportsmanDTO;
import com.iot.spring.spr.domain.Sportsman;
import com.iot.spring.spr.exceptions.AlreadyExistsSportInSportsmanException;
import com.iot.spring.spr.exceptions.NoSuchCategoryException;
import com.iot.spring.spr.exceptions.NoSuchSportException;
import com.iot.spring.spr.exceptions.NoSuchSportsmanException;
import com.iot.spring.spr.exceptions.SportsmanHasNotSportException;
import com.iot.spring.spr.service.SportsmanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SportsmanController {
	@Autowired
	SportsmanService sportsmanService;

	@GetMapping(value = "/api/sportsman/category/{category_id}")
	public ResponseEntity<List<SportsmanDTO>> getSportsamnByCategoryID(@PathVariable Integer category_id)
			throws NoSuchCategoryException, NoSuchSportsmanException, NoSuchSportException {
		List<Sportsman> sportsmanList = sportsmanService.getSportsmanByCategoryId(category_id);

		Link link = linkTo(methodOn(SportsmanController.class).getAllSportsmans()).withSelfRel();

		List<SportsmanDTO> sportsmanDTO = new ArrayList<>();
		for (Sportsman entity : sportsmanList) {
			Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
			SportsmanDTO dto = new SportsmanDTO(entity, selfLink);
			sportsmanDTO.add(dto);
		}

		return new ResponseEntity<>(sportsmanDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/api/sportsman/{sportsman_id}")
	public ResponseEntity<SportsmanDTO> getSportsman(@PathVariable Integer sportsman_id)
			throws NoSuchSportsmanException, NoSuchSportException {
		Sportsman sportsman = sportsmanService.getSportsman(sportsman_id);
		Link link = linkTo(methodOn(SportsmanController.class).getSportsman(sportsman_id)).withSelfRel();

		SportsmanDTO personDTO = new SportsmanDTO(sportsman, link);

		return new ResponseEntity<>(personDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/api/sportsman")
	public ResponseEntity<List<SportsmanDTO>> getAllSportsmans() throws NoSuchSportsmanException, NoSuchSportException {
		List<Sportsman> sportsmanList = sportsmanService.getAllSportsmans();
		Link link = linkTo(methodOn(SportsmanController.class).getAllSportsmans()).withSelfRel();

		List<SportsmanDTO> sportsmanDTO = new ArrayList<>();
		for (Sportsman entity : sportsmanList) {
			Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
			SportsmanDTO dto = new SportsmanDTO(entity, selfLink);
			sportsmanDTO.add(dto);
		}

		return new ResponseEntity<>(sportsmanDTO, HttpStatus.OK);
	}



	@PostMapping(value = "/api/sportsman/category/{category_id}")
	public ResponseEntity<SportsmanDTO> addSportsman(@RequestBody Sportsman newSportsman,
			@PathVariable Integer category_id) throws NoSuchSportsmanException, NoSuchCategoryException, NoSuchSportException {
		sportsmanService.createSportsman(newSportsman, category_id);
		Link link = linkTo(methodOn(SportsmanController.class).getSportsman(newSportsman.getId())).withSelfRel();

		SportsmanDTO sportsmanDTO = new SportsmanDTO(newSportsman, link);

		return new ResponseEntity<>(sportsmanDTO, HttpStatus.CREATED);
	}

	@PutMapping(value = "/api/sportsman/{sportsman_id}/category/{category_id}")
	public ResponseEntity<SportsmanDTO> updateSportsman(@RequestBody Sportsman uSportsman,
			@PathVariable Integer sportsman_id, @PathVariable Integer category_id)
			throws NoSuchSportsmanException, NoSuchCategoryException, NoSuchSportException {
		sportsmanService.updateSportsman(uSportsman, sportsman_id, category_id);
		Sportsman sportsman = sportsmanService.getSportsman(sportsman_id);
		Link link = linkTo(methodOn(SportsmanController.class).getSportsman(sportsman_id)).withSelfRel();

		SportsmanDTO sportsmanDTO = new SportsmanDTO(sportsman, link);

		return new ResponseEntity<>(sportsmanDTO, HttpStatus.OK);
	}

    @DeleteMapping(value = "/api/sportsman/{sportsman_id}")
    public  ResponseEntity deleteSportsman(@PathVariable Integer sportsman_id) throws Exception  {
        sportsmanService.deleteSportsman(sportsman_id);
        return new ResponseEntity(HttpStatus.OK);
    }


  

}
