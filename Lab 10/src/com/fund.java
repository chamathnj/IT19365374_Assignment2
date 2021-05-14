package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class fund {	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf\", \"root\", \"root123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readFunds() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Funder Name</th> <th>Funder Address</th><th>Company Name</th>"
					+ "<th>Company Address</th><th>Funder Email</th><th>Funder Phone</th><th>Fund Amount</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from fund";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String fundID = Integer.toString(rs.getInt("fundID"));
				String funderName = rs.getString("funderName");
				String funderAddress = rs.getString("funderAddress");
				String companyName = rs.getString("companyName");
				String companyAddress = rs.getString("companyAddress");
				String funderEmail = rs.getString("funderEmail");
				String funderPhone = rs.getString("funderPhone");
				String fundAmount = Double.toString(rs.getDouble("fundAmount"));
				
				// Add into the html table
				output += "<tr><td><input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='" + fundID
						+ "'>" + funderName + "</td>";
				output += "<td>" + funderAddress + "</td>";
				output += "<td>" + companyName + "</td>";
				output += "<td>" + companyAddress + "</td>";
				output += "<td>" + funderEmail + "</td>";
				output += "<td>" + funderPhone + "</td>";
				output += "<td>" + fundAmount + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-fundid='"
						+ fundID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the funds.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertFund(String fname, String faddress, String cname, String caddress, String email, String phone, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into fund (`fundID`,`funderName`,`funderAddress`,`companyName`,`companyAddress`,`funderEmail`,`funderPhone`,`fundAmount`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, faddress);
			preparedStmt.setString(4, cname);
			preparedStmt.setString(5, caddress);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, phone);
			preparedStmt.setDouble(8, Double.parseDouble(amount));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the fund.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateFund(String ID, String fname, String faddress, String cname, String caddress, String email, String phone, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE fund SET funderName=?,funderAddress=?,companyName=?,companyAddress=?,funderEmail=?,funderPhone=?,fundAmount=? WHERE fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, fname);
			preparedStmt.setString(2, faddress);
			preparedStmt.setString(3, cname);
			preparedStmt.setString(4, caddress);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, phone);
			preparedStmt.setDouble(7, Double.parseDouble(amount));
			preparedStmt.setInt(8, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the fund.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteFund(String fundID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from fund where fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" + newFunds + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":  \"Error while deleting the fund.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
