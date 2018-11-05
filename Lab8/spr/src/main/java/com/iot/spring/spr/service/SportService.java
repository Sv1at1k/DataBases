package com.iot.spring.spr.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.spr.Repository.SportRepository;
import com.iot.spring.spr.Repository.SportsmanRepository;
import com.iot.spring.spr.domain.Sport;
import com.iot.spring.spr.domain.Sportsman;
import com.iot.spring.spr.exceptions.ExistsSportsmanForSportException;
import com.iot.spring.spr.exceptions.NoSuchSportException;
import com.iot.spring.spr.exceptions.NoSuchSportsmanException;


@Service
public class SportService {
	   @Autowired
	    SportRepository sportRepository;

	    @Autowired
	    SportsmanRepository sportsmanRepository;

	   

	    public Sport getSport(Integer sport_id) throws NoSuchSportException {
	    	Sport sport = sportRepository.findById(sport_id).get();
	        if (sport == null) throw new NoSuchSportException();
	        return sport;
	    }

	    public List<Sport> getAllSport() {
	        return sportRepository.findAll();
	    }

	    @Transactional
	    public void createSport(Sport sport) {
	        sportRepository.save(sport);
	    }

	    @Transactional
	    public void updateSport(Sport uSport, Integer sport_id) throws NoSuchSportException {
	        Sport sport = sportRepository.findById(sport_id).get();
	        if (sport == null) throw new NoSuchSportException();
	        //update
	       sport.setName(uSport.getName());
	    }

	    @Transactional
	    public void deleteSport(Integer sport_id) throws NoSuchSportException, ExistsSportsmanForSportException  {

	        Sport sport = sportRepository.findById(sport_id).get();

	        if (sport == null) throw new NoSuchSportException();
	  
	        sportRepository.delete(sport);
	    }
}
