package com.iot.spring.spr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.spring.spr.domain.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
