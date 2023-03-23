package fetchData;

import java.util.List;
import java.util.Scanner;
import storeData.DatabaseManager;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);
	private static final DatabaseManager databaseManager = new DatabaseManager();
	private static final APIManager apiManager = new APIManager(databaseManager);

	public static void main(String[] args) {
		boolean quit = false;
		while (!quit) {
			printMenu();
			String choice = scanner.nextLine();
			switch (choice) {
			case "1":
				initializeDatabase();
				break;
			case "2":
				backupDatabase();
				break;
			case "3":
				deleteTables();
				break;
			case "4":
				fetchDataFromAPI();
				break;
			case "5":
				fetchDataFromDatabase();
				break;
			case "6":
				dumpDataToFile();
				break;
			case "7":
				printUniversitiesByCountry();
				break;
			case "8":
				quit = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
		System.out.println("Goodbye!");
	}

	private static void printMenu() {
		System.out.println("\nMenu:");
		System.out.println("1. Initialize Database");
		System.out.println("2. Backup Database");
		System.out.println("3. Delete Tables");
		System.out.println("4. Fetch Data From API");
		System.out.println("5. Fetch Data From Database");
		System.out.println("6. Dump Data To File");
		System.out.println("7. Print Universities By Country");
		System.out.println("8. Quit");
		System.out.print("Please enter your choice: ");
	}

	private static void initializeDatabase() {
		System.out.print("Please enter the name of the database: ");
		String dbName = scanner.nextLine();
		databaseManager.initializeDatabase(dbName);
	}

	private static void backupDatabase() {
		System.out.print("Please enter the name of the backup file: ");
		String fileName = scanner.nextLine();
		// databaseManager.backupDatabase(fileName);
	}

	private static void deleteTables() {
	}

	private static void fetchDataFromAPI() {
		System.out.print("Please enter the name of the country: ");
		String countryName = scanner.nextLine();
		apiManager.fetchUniversitiesByCountry(countryName);
	}

	private static void fetchDataFromDatabase() {
		System.out.print("Please enter the name of the country: ");
		String countryName = scanner.nextLine();
		databaseManager.printUniversitiesByCountry(countryName);
	}

	private static void dumpDataToFile() {
		System.out.print("Please enter the name of the file: ");
		String fileName = scanner.nextLine();
		databaseManager.dumpDataToFile(fileName);
	}

	private static void printUniversitiesByCountry() {
		System.out.print("Please enter the name of the country: ");
		String countryName = scanner.nextLine();
		databaseManager.printUniversitiesByCountry(countryName);
	}

	private static void fetchAllUniversities() {
		List<University> universities = databaseManager.getAllUniversities();
		for (University university : universities) {
			System.out.println(university);
		}
	}
}