package com.iot.spring.spr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.spring.spr.domain.Category;
import com.iot.spring.spr.domain.Sport;

public interface SportRepository  extends JpaRepository<Sport, Integer> {

}
