package JDBCCaseStudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Product_Iventory {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
	        Connection con = DBConnection.getConnection();  // Reusing the DB connection

	        if (con == null) {
	            System.out.println("Cannot proceed without DB connection.");
	            return;
	        }

	        try {
	            Statement stmt = con.createStatement();

	            while (true) {
	                System.out.println("\n--- Product Inventory Menu ---");
	                System.out.println("1. Add Product");
	                System.out.println("2. View Products");
	                System.out.println("3. Update Quantity");
	                System.out.println("4. Delete Product");
	                System.out.println("5. Exit");
	                System.out.print("Enter choice: ");
	                int choice = sc.nextInt();

	                switch (choice) {
	                    case 1:
	                        System.out.print("Product ID: ");
	                        int id = sc.nextInt();
	                        sc.nextLine();
	                        System.out.print("Product Name: ");
	                        String name = sc.nextLine();
	                        System.out.print("Quantity: ");
	                        int qty = sc.nextInt();
	                        System.out.print("Price: ");
	                        double price = sc.nextDouble();

	                        String insert = "INSERT INTO products VALUES (?, ?, ?, ?)";
	                        PreparedStatement ps1 = con.prepareStatement(insert);
	                        ps1.setInt(1, id);
	                        ps1.setString(2, name);
	                        ps1.setInt(3, qty);
	                        ps1.setDouble(4, price);
	                        ps1.executeUpdate();
	                        System.out.println("✅ Product added.");
	                        break;

	                    case 2:
	                        ResultSet rs = stmt.executeQuery("SELECT * FROM products");
	                        System.out.println("\nProduct Inventory:");
	                        while (rs.next()) {
	                            System.out.println(rs.getInt(1) + " | " +
	                                               rs.getString(2) + " | Qty: " +
	                                               rs.getInt(3) + " | ₹" +
	                                               rs.getDouble(4));
	                        }
	                        break;

	                    case 3:
	                        System.out.print("Product ID to update: ");
	                        int pid = sc.nextInt();
	                        System.out.print("New Quantity: ");
	                        int newQty = sc.nextInt();

	                        String update = "UPDATE products SET quantity = ? WHERE product_id = ?";
	                        PreparedStatement ps2 = con.prepareStatement(update);
	                        ps2.setInt(1, newQty);
	                        ps2.setInt(2, pid);
	                        ps2.executeUpdate();
	                        System.out.println("✅ Quantity updated.");
	                        break;

	                    case 4:
	                        System.out.print("Product ID to delete: ");
	                        int did = sc.nextInt();

	                        String delete = "DELETE FROM products WHERE product_id = ?";
	                        PreparedStatement ps3 = con.prepareStatement(delete);
	                        ps3.setInt(1, did);
	                        ps3.executeUpdate();
	                        System.out.println("✅ Product deleted.");
	                        break;

	                    case 5:
	                        con.close();
	                        sc.close();
	                        System.exit(0);

	                    default:
	                        System.out.println("Invalid choice.");
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	}

}
