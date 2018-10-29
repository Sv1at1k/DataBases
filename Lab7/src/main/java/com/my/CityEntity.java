package com.my;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "city", schema = "db_jdbc", catalog = "")
public class CityEntity {
    private String city;
    private Collection<PersonEntity> peopleByCity;

    public CityEntity(){}
    public CityEntity(String c){
        city=c;
    }

    @Id
    @Column(name = "City", nullable = false, length = 25)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityEntity that = (CityEntity) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return city != null ? city.hashCode() : 0;
    }

    @OneToMany(mappedBy = "cityByCity")
    public Collection<PersonEntity> getPeopleByCity() {
        return peopleByCity;
    }

    public void setPeopleByCity(Collection<PersonEntity> peopleByCity) {
        this.peopleByCity = peopleByCity;
    }
}
