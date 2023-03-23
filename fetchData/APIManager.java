package fetchData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import storeData.DatabaseManager;

public class APIManager {
	private static final String API_URL = "http://universities.hipolabs.com/search?country=Oman";

	private DatabaseManager dbManager;

	public APIManager(DatabaseManager dbManager) {
		this.dbManager = dbManager;
	}

	public void fetchDataAndStoreInDatabase(String country) {
		try {
			String apiUrl = API_URL + "?country=" + country;
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			StringBuilder json = new StringBuilder();

			while ((output = br.readLine()) != null) {
				json.append(output);
			}

			conn.disconnect();

			Gson gson = new Gson();
			University[] universities = gson.fromJson(json.toString(), University[].class);

			dbManager.insertUniversities(universities);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}