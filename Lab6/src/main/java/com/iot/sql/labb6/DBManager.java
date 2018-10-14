package com.iot.sql.labb6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;
import java.util.Scanner;

public class DBManager {

	private String dbUrl;
	private String dbName;
	private String dbPassword;
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet rs = null;

	public void startWork() throws IOException, ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Введіть ім'я бази данних: ");
		this.dbUrl = "jdbc:mysql://localhost:3306/" + scan.nextLine() + "?serverTimezone=UTC&useSSL=false";
		System.out.println("Введіть логін: ");
		this.dbName = scan.nextLine();
		System.out.println("Введіть пароль:");
		this.dbPassword = scan.nextLine();

//		this.dbUrl = "jdbc:mysql://localhost:3306/" + "tables" + "?serverTimezone=UTC&useSSL=false";
//		this.dbName = "root";
//		this.dbPassword = "admin";

		System.out.println("   Виберіть пункт з меню");
		System.out.println("1.Вивести данні з таблиці;");
		System.out.println("2.Вставити данні у таблицю;");
		System.out.println("3.Видалити данні з таблиці;");
		System.out.println("4.Відредагувати данні у таблиці;");
		
		int result = scan.nextInt();
		
		if (result == 1) {
			System.out.println("Введіть ім'я таблички");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			read(reader.readLine());
			startWork();
		} else if (result == 2) {
			System.out.println("Введіть ім'я таблички");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			insertData(reader.readLine());
			startWork();
		} else if (result == 3) {
			System.out.println("Введіть ім'я таблички");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String tableName = reader.readLine();
			System.out.println("Введіть id запису який бажаєте видалити ");
			int id = Integer.parseInt(reader.readLine());
			deleteData(tableName, id);
			startWork();

		} else if (result == 4) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Введіть ім'я таблички");
			String tableName = reader.readLine();

			System.out.println("Введіть id запису");
			int id = Integer.parseInt(reader.readLine());

			System.out.println("Введіть нове імя запису");
			String name = reader.readLine();

			alterData(tableName, id, name);

		} else {
			System.out.println("ВСЕ НЕПРАВИЛЬНО!");
			startWork();
		}
	}

	private void read(String tableName) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl, dbName, dbPassword);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM " + tableName);

			System.out.println("Table " + tableName + " --------------------\n");
			switch (tableName) {
			case "table2":
				System.out.println("id " + " name " + " age");
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					System.out.println(id + " " + " " + name + " " + age);
				}
				break;
			case "table3":
				System.out.println("id " + " name " + " age");
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					System.out.println(id + " " + " " + name + " " + age);
				}
				break;
			case "table1":
				System.out.println(" id " + " table3_id " + " name " + " age ");
				while (rs.next()) {
					int id = rs.getInt("id");
					int table3_id = rs.getInt("table3_id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					System.out.println(id + " " + table3_id + " " + name + " " + age);

				}
				break;

			case "joint":
				System.out.println("table1_id " + " table2_id ");
				while (rs.next()) {
					int table1_id = rs.getInt("table1_id");
					int table2_id = rs.getInt("table2_id");
					System.out.println(table1_id + " " + table2_id);

				}
				break;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}

	}

	private void insertData(String tableName) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(dbUrl, dbName, dbPassword);
		PreparedStatement preparedState;

		switch (tableName) {

		case "table2":
			preparedState = connection.prepareStatement("INSERT INTO table2(id,name,age) VALUES (?,?,?)");

			BufferedReader IDreader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Введіть id");
			int id = Integer.parseInt(IDreader.readLine());
			preparedState.setInt(1, id);

			System.out.println("Введіть name");
			BufferedReader nameReader = new BufferedReader(new InputStreamReader(System.in));
			String name = nameReader.readLine();
			preparedState.setString(2, name);

			System.out.println("Введіть age");
			BufferedReader ageReader = new BufferedReader(new InputStreamReader(System.in));
			int age = Integer.parseInt(ageReader.readLine());
			preparedState.setInt(3, age);

			int insertedRows = preparedState.executeUpdate();
			System.out.println("Кількість вставлених записів " + insertedRows);
			break;
		case "table3":

			preparedState = connection.prepareStatement("INSERT INTO table3(id,name,age) VALUES (?,?,?)");
			BufferedReader IDreader3 = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Введіть id");
			int id3 = Integer.parseInt(IDreader3.readLine());
			preparedState.setInt(1, id3);

			System.out.println("Введіть name");
			BufferedReader nameReader3 = new BufferedReader(new InputStreamReader(System.in));
			String name3 = nameReader3.readLine();
			preparedState.setString(2, name3);

			System.out.println("Введіть age");
			BufferedReader ageReader3 = new BufferedReader(new InputStreamReader(System.in));
			int age3 = Integer.parseInt(ageReader3.readLine());
			preparedState.setInt(3, age3);

			int insertedRows3 = preparedState.executeUpdate();
			System.out.println("Кількість вставлених записів " + insertedRows3);
			break;
		case "table1":
			preparedState = connection.prepareStatement("INSERT INTO table1(id,table3_id,name,age) VALUES(?,?,?,?)");
			BufferedReader IDreader1 = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Введіть id");
			int id1 = Integer.parseInt(IDreader1.readLine());
			preparedState.setInt(1, id1);

			System.out.println("Введіть table3_id");
			int table3_id = Integer.parseInt(IDreader1.readLine());
			preparedState.setInt(2, table3_id);

			System.out.println("Введіть name");
			BufferedReader nameReader1 = new BufferedReader(new InputStreamReader(System.in));
			String name1 = nameReader1.readLine();
			preparedState.setString(3, name1);

			System.out.println("Введіть age");
			BufferedReader ageReader1 = new BufferedReader(new InputStreamReader(System.in));
			int age1 = Integer.parseInt(ageReader1.readLine());
			preparedState.setInt(4, age1);

			int insertedRows1 = preparedState.executeUpdate();
			System.out.println("Кількість вставлених записів " + insertedRows1);
			break;
		case "joint":
			preparedState = connection.prepareStatement("INSERT INTO joint(table1_id,table2_id) VALUES(?,?)");
			System.out.println("Введіть table1_id");
			BufferedReader table1_id = new BufferedReader(new InputStreamReader(System.in));
			String table1Id = table1_id.readLine();
			preparedState.setString(1, table1Id);

			System.out.println("Введіть table2_id");
			BufferedReader table2_id = new BufferedReader(new InputStreamReader(System.in));
			String table2Id = table1_id.readLine();
			preparedState.setString(2, table2Id);

			int insertedRowsJoint = preparedState.executeUpdate();
			System.out.println("Кількість вставлених записів " + insertedRowsJoint);
			break;
		}

	}

	private void deleteData(String tableName, int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(dbUrl, dbName, dbPassword);
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id=?");
		preparedStatement.setInt(1, id);

		int deletedRows = preparedStatement.executeUpdate();
		System.out.println("Видалено рядків " + deletedRows);

	}

	private void alterData(String tableName, int id, String name) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(dbUrl, dbName, dbPassword);
		statement = connection.createStatement();
		statement.execute("UPDATE " + tableName + " SET name='" + name + "' WHERE id='" + id + "';");
		System.out.println("Рядок змінено");

	}
}
