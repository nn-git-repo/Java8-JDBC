package JDBCCaseStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CourseRegistrationSystem {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_db", "root", "root");
            Statement stmt = con.createStatement();

            while (true) {
                System.out.println("\n--- Course Registration Menu ---");
                System.out.println("1. Add Course");
                System.out.println("2. View All Courses");
                System.out.println("3. Update Course");
                System.out.println("4. Delete Course");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Course ID: ");
                        int id = sc.nextInt();
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Course Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Faculty Name: ");
                        String faculty = sc.nextLine();
                        System.out.print("Enter Credits: ");
                        int credits = sc.nextInt();

                        String insert = "INSERT INTO courses VALUES (?, ?, ?, ?)";
                        PreparedStatement ps1 = con.prepareStatement(insert);
                        ps1.setInt(1, id);
                        ps1.setString(2, name);
                        ps1.setString(3, faculty);
                        ps1.setInt(4, credits);
                        ps1.executeUpdate();
                        System.out.println("Course added successfully.");
                        break;

                    case 2:
                        ResultSet rs = stmt.executeQuery("SELECT * FROM courses");
                        System.out.println("\nCourses Available:");
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + " - " + rs.getString(2) + " | " + rs.getString(3) + " | Credits: " + rs.getInt(4));
                        }
                        break;

                    case 3:
                        System.out.print("Enter Course ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Faculty Name: ");
                        String newFaculty = sc.nextLine();
                        System.out.print("Enter new Credits: ");
                        int newCredits = sc.nextInt();

                        String update = "UPDATE courses SET faculty = ?, credits = ? WHERE course_id = ?";
                        PreparedStatement ps2 = con.prepareStatement(update);
                        ps2.setString(1, newFaculty);
                        ps2.setInt(2, newCredits);
                        ps2.setInt(3, updateId);
                        ps2.executeUpdate();
                        System.out.println("Course updated successfully.");
                        break;

                    case 4:
                        System.out.print("Enter Course ID to delete: ");
                        int deleteId = sc.nextInt();
                        String delete = "DELETE FROM courses WHERE course_id = ?";
                        PreparedStatement ps3 = con.prepareStatement(delete);
                        ps3.setInt(1, deleteId);
                        ps3.executeUpdate();
                        System.out.println("Course deleted.");
                        break;

                    case 5:
                        con.close();
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
