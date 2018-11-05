package com.iot.spring.spr.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sport")
public class Sport {
	@Id
	@Column(name = "sport_id")
	private Integer id;

	@Column(name = "name")
	private String name;


	
	
	public void setId(Integer id) {
		this.id = id;

	}

	public Integer getId() {
		return id;

	}

	public void setName(String name) {
		this.name = name;

	}

	public String getName() {
		return name;

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Sport that = (Sport) o;
		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
