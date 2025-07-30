package JDBCCaseStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


		    public static Connection getConnection() {
		        Connection con = null;

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");

		            con = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/inventory_db",
		                "root",
		                "root"
		            );

		            System.out.println("Connected to database!");

		        } catch (ClassNotFoundException e) {
		            System.out.println("JDBC Driver not found.");
		            e.printStackTrace();
		        } catch (SQLException e) {
		            System.out.println("Connection Failed.");
		            e.printStackTrace();
		        }

		        return con;
		    }
		

	}


