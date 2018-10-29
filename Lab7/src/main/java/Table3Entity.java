import javax.persistence.*;
@Entity
@Table(name = "table3",schema = "tables",catalog = "")
public class Table3Entity {
	private int id;
	
	private String name;
	
	private int age;
	
	
	public Table3Entity() {
	}

	public Table3Entity(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	@Id
	@Column (name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	@Column(name = "name",nullable = true,length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Column(name = "age")
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Table3Entity that = (Table3Entity) o;

	        if (id != that.id) return false;
	        if (age != that.age) return false;
	        if (name != null ? !name.equals(that.name) : that.name != null) return false;
	       

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        int result = id;
	        result = 31 * result + (name != null ? name.hashCode() : 0);
	        result = 31 * result + age;
	        return result;
	    }
	
	
	
}
