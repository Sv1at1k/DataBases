import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DBManager {
	private static SessionFactory ourSessionFactory;
	static {
		try { // Create the SessionFactory from hibernate.cfg.xml
			ourSessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return ourSessionFactory.openSession(); // return opened session
	}

	public void menu() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Make your choice!");
		System.out.println("1.Select from db");
		System.out.println("2.Insert into db");
		System.out.println("3.Delete from db");
		System.out.println("4.Alter table");
		System.out.println();
		int result = Integer.parseInt(reader.readLine());
		System.out.println("Enter table name:");
		String table = reader.readLine();
		int id;
		switch (result) {
		case 1:
			select(table);
			menu();
			break;
		case 2:
			insert(table);
			menu();
			break;

		case 3:
			System.out.println("Enter id: ");
			id = Integer.parseInt(reader.readLine());
			delete(table, id);
			menu();
			break;
		case 4:
			System.out.println("Enter id: ");
			id = Integer.parseInt(reader.readLine());
			alter(table, id);
			menu();
		}

	}

	private void alter(String table, int id) throws IOException {
		Session session = getSession();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Query query;
			System.out.println("Enter new name:");
			String name = reader.readLine();
			System.out.println("Enter new age:");
			int age = Integer.parseInt(reader.readLine());

			switch (table) {
			case "table1":
				Table1Entity table1Entity = session.load(Table1Entity.class, id);
				if (table1Entity != null) {
					session.beginTransaction();
					query = session.createQuery("update Table1Entity set name=:name  where id = :id");
					query.setParameter("name", name);
					query.setParameter("id", id);
					int result = query.executeUpdate();
					session.getTransaction().commit();
					System.out.println("end update table1: " + result);
				}
				break;
			case "table2":
				Table2Entity table2Entity = session.load(Table2Entity.class, id);
				if (table2Entity != null) {
					session.beginTransaction();
					query = session.createQuery("update Table2Entity set name=:name  where id = :id");
					query.setParameter("name", name);
					query.setParameter("id", id);
					int result = query.executeUpdate();
					session.getTransaction().commit();
					System.out.println("end update table2: " + result);
				}
				break;
			case "table3":
				Table3Entity table3Entity = session.load(Table3Entity.class, id);
				if (table3Entity != null) {
					session.beginTransaction();
					query = session.createQuery("update Table3Entity set name=:name  where id = :id");
					query.setParameter("name", name);
					query.setParameter("id", id);
					int result = query.executeUpdate();
					session.getTransaction().commit();
					System.out.println("end update table3: " + result);
				}
				break;

			}

		} finally {
			session.close();
			System.exit(0);

		}

	}

	private void delete(String table, int id) throws IOException {

		Session session = getSession();
		session.beginTransaction();
		Query query;
		int result;
		try {

			switch (table) {
			case "table1":
				query = session.createQuery("delete Table1Entity where id = :id");
				query.setParameter("id", id);
				result = query.executeUpdate();
				System.out.println(result + ":rows was deleted");
				break;

			case "table2":
				query = session.createQuery("delete Table2Entity where id = :id");
				query.setParameter("id", id);
				result = query.executeUpdate();
				System.out.println(result + ":rows was deleted");
				break;

			case "table3":
				query = session.createQuery("delete Table3Entity where id = :id");
				query.setParameter("id", id);
				result = query.executeUpdate();
				System.out.println(result + ":rows was deleted");
				break;

			}
		} finally {
			session.close();
			System.exit(0);

		}

	}

	private void insert(String table) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Session session = getSession();
		session.beginTransaction();
		int id;
		String name;
		int age;
		Query query;

		try {

			switch (table) {

			case "table1":
				System.out.println("Enter  id:");
				id = Integer.parseInt(reader.readLine());
				System.out.println("Enter  table3_id:");
				int table3_id = Integer.parseInt(reader.readLine());
				System.out.println("Enter name:");
				name = reader.readLine();
				System.out.println("Enter age:");
				age = Integer.parseInt(reader.readLine());
				Table3Entity table3 = new Table3Entity();
				table3.setId(table3_id);
				Table1Entity table1 = new Table1Entity(id, table3, name, age);
				session.save(table1);
				session.getTransaction().commit();
				System.out.println("Done!");
				break;

			case "table2":
				System.out.println("Enter  id:");
				id = Integer.parseInt(reader.readLine());
				System.out.println("Enter name:");
				name = reader.readLine();
				System.out.println("Enter age:");
				age = Integer.parseInt(reader.readLine());
				Table2Entity table2 = new Table2Entity(id, name, age);
				session.save(table2);
				session.getTransaction().commit();
				System.out.println("Done!");
				break;
			case "table3":
				System.out.println("Enter  id:");
				id = Integer.parseInt(reader.readLine());
				System.out.println("Enter name:");
				name = reader.readLine();
				System.out.println("Enter age:");
				age = Integer.parseInt(reader.readLine());
				Table3Entity tabble = new Table3Entity(id, name, age);
				session.save(tabble);
				session.getTransaction().commit();
				System.out.println("Done!");
				break;
			case "joint":
				System.out.println("Enter table1_id");
				int table1ID = Integer.parseInt(reader.readLine());
				System.out.println("Enter table2_id");
				int table2ID = Integer.parseInt(reader.readLine());

				query = session.createQuery("from " + "table1Entity where id = :id");
				query.setParameter("id", table1ID);

				if (!query.list().isEmpty()) {

					Table1Entity table1Entity = (Table1Entity) query.list().get(0);

					query = session.createQuery("from " + "table2Entity where id = :id");
					query.setParameter("id", table2ID);
					if (!query.list().isEmpty()) {

						Table2Entity table2Entity = (Table2Entity) query.list().get(0);
						session.beginTransaction();
						table1Entity.addTableEntity(table2Entity);
						session.save(table1Entity);
						session.getTransaction().commit();
						System.out.println("end insert ");
					} else {
						System.out.println("There is no the table2");
					}
				} else {
					System.out.println("There is no this table1");
				}

				break;

			}

		} finally {
			session.close();
			System.exit(0);

		}

	}

	private void select(String table) {
		Session session = getSession();
		try {
			Query query;

			switch (table) {
			case "table1":
				query = session.createQuery("from Table1Entity");
				System.out.println(query.list().size());
				System.out.format("%3s %-12s %-12s %-12s \n", "id", "table3_id", "name", "age");

				break;
			case "table2":
				query = session.createQuery("from Table2Entity");
				System.out.println(query.list().size());
				List<Table2Entity> testList = query.list();
				System.out.format("%3s %-12s %-12s \n", "id", "name", "age");
				for (Table2Entity obj : testList) {
					Table2Entity book =  obj;
					System.out.format("%3d %-18s %-18s \n", book.getId(), book.getName(), book.getAge());
				}

				break;
			case "table3":
				query = session.createQuery("from Table3Entity");
				System.out.println(query.list().size());
				System.out.format("%3s %-12s %-12s %s\n", "id", "name", "age");

				break;

			case "joint":
				query = session.createQuery("from joint");
				System.out.println(query.list().size());
				System.out.format("%3s %-12s %-12s %s\n", "id", "name", "age");

			}

		} finally {
			session.close();
			System.exit(0);
		}

	}

}
