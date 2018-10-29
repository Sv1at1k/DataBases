import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
@Table (name = "table1",schema = "tables", catalog = "")
public class Table1Entity {
	private int id;
	private String name;
	private int age;
	private Table3Entity table3;
	private List<Table2Entity> table2 = new ArrayList<Table2Entity>();
 	
	public Table1Entity() {
	}

	public Table1Entity(int id,Table3Entity table3 ,String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.table3 = table3;
	}
	
	
	@Id
	@Column (name = "id",nullable = false)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	@Column(name = "name",nullable = false,length = 100)
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
	

	@ManyToOne(cascade = CascadeType.ALL)
	public Table3Entity getTable3() {
		return table3;
	}
	public void setTable3(Table3Entity table3) {
		this.table3 = table3;
	}
	
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "joint",joinColumns = { @JoinColumn(name = "table1_id") }, inverseJoinColumns = { @JoinColumn(name = "table2_id") })
	public List<Table2Entity> getTable2() {
		return table2;
	}
	public void setTable2(List<Table2Entity> table2) {
		this.table2 = table2;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Table1Entity that = (Table1Entity) o;

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
	@Override
	public String toString() {
	return this.id + " " + this.name + " " +this.age; 
	}
	
	public void addTableEntity(Table2Entity table2Entity){
        if(!getTable2().contains(table2Entity)){
            getTable2().add(table2Entity);
        }
        if(!table2Entity.getTable1Entity().contains(this)){
        	table2Entity.getTable1Entity().add(this);
        }
    }


}
