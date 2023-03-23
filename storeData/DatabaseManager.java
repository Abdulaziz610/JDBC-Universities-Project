package storeData;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fetchData.University;

public class DatabaseManager {

	private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=JDBC Universities Project;"
			+ "encrypt=true;" + "trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "root";

	private static Connection conn;

	public DatabaseManager() {
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void initializeDatabase(String databaseName) {
		try {
			// create database if it does not already exist
			conn.createStatement().execute("IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = '" + databaseName
					+ "') CREATE DATABASE " + databaseName);
			conn.createStatement().execute("USE " + databaseName);

		
			conn.createStatement().execute(
					"IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Universities') CREATE TABLE Universities (Name VARCHAR(255), Country VARCHAR(255), StateProvince VARCHAR(255), AlphaTwoCode VARCHAR(2), Domains VARCHAR(255), WebPages VARCHAR(255))");
			conn.createStatement().execute(
					"IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Countries') CREATE TABLE Countries (Name VARCHAR(255), AlphaTwoCode VARCHAR(2))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertUniversities(University[] universities) {
		// insert universities data into the database
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Universities VALUES (?, ?, ?, ?, ?, ?)");
			for (University university : universities) {
				pstmt.setString(1, university.getName());
				pstmt.setString(2, university.getCountry());
				pstmt.setString(3, university.getState_province());
				pstmt.setString(4, university.getAlpha_two_code());
				pstmt.setString(5, String.join(",", university.getDomains()));
				pstmt.setString(6, String.join(",", university.getWeb_pages()));
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteTable(String tableName) throws SQLException {
		String sql = "DROP TABLE IF EXISTS " + tableName;
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
			System.out.println(tableName + " table has been deleted.");
		}
	}

	public void printUniversitiesByCountry(String countryName) {
		try {

			Statement statement = conn.createStatement();

			String query = "SELECT * FROM universities WHERE country = '" + countryName + "'";
			ResultSet resultSet = statement.executeQuery(query);
			System.out.println("*******************************************************************");
			System.out.println("                Universities in " + countryName + ":");
			System.out.println("*******************************************************************");
			while (resultSet.next()) {
				// int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String webPage = resultSet.getString("webpages");
				String domain = resultSet.getString("domains");
				System.out.println("----------------------------------------------------------");

				System.out.println(name + "\t" + " :: " + domain + "\t" + " :: " + webPage);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// other methods for retrieving data, backing up the database, and deleting
	// tables
}