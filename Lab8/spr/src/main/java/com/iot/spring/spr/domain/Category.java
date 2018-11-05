package com.iot.spring.spr.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "category")
public class Category {
	@Id
	@Column(name = "category_id", nullable = false)
	private Integer id;
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	@OneToMany(mappedBy = "category")
	private List<Sportsman> sportsmans;

	public Category() {
	};

	public Category(String name) {
		this.name = name;

	}

	public void setID(Integer id) {
		this.id = id;

	}

	public Integer getID() {
		return id;

	}

	public void setName(String name) {
		this.name = name;

	}

	public String getName() {
		return name;

	}
	
	 public List<Sportsman> getSportsman() {
	        return sportsmans;
	    }
	    public void setSportsman(List<Sportsman> sportsman) {
	        this.sportsmans = sportsman;
	    }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Category that = (Category) o;
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
