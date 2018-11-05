package com.iot.spring.spr.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sportsman")
public class Sportsman {
	@Id
	@Column(name = "sportsman_id", nullable = true)
	private Integer id;

	@Column(name = "name", nullable = true)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	

	
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

	public void setCategory(Category catregory) {
		this.category = catregory;

	}

	public Category getCategory() {
		return category;
}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Sportsman that = (Sportsman) o;
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
