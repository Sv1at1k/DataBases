
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "table2", schema = "tables", catalog = "")
public class Table2Entity {
	private int id;

	private String name;

	private int age;

	private List<Table1Entity> table1Entity;

	public Table2Entity() {
	}

	public Table2Entity(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@Id
	@Column(name = "id",nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age",nullable = false)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Table2Entity that = (Table2Entity) o;

		if (id != that.id)
			return false;
		if (age != that.age)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + age;
		return result;
	}

	@Override
	public String toString() {
		return this.id + " " + this.name + " " + this.age;
	}

	
	@ManyToMany(mappedBy = "table2")
	public List<Table1Entity> getTable1Entity() {
		return table1Entity;
	}

	public void setTable1Entity(List<Table1Entity> table1Entity) {
		this.table1Entity = table1Entity;
	}
}
