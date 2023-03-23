package fetchData;

import java.util.List;

public class University {
	private String state_province;
	private List<String> domains;
	private String country;
	private List<String> web_pages;
	private String name;
	private String alpha_two_code;

	public String getState_province() {
		return state_province;
	}

	
	// constructors, getters and setters
	
	public University(String name2, String country2, String alphaTwoCode, String stateProvince, List<String> list,
			List<String> list2) {
		this.name = name2;
		this.country = country2;
		this.alpha_two_code = alphaTwoCode;
		this.state_province = stateProvince;
		this.domains = list;
		this.web_pages = list2;

	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public List<String> getDomains() {
		return domains;
	}

	public void setDomains(List<String> domains) {
		this.domains = domains;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getWeb_pages() {
		return web_pages;
	}

	public void setWeb_pages(List<String> web_pages) {
		this.web_pages = web_pages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlpha_two_code() {
		return alpha_two_code;
	}

	public void setAlpha_two_code(String alpha_two_code) {
		this.alpha_two_code = alpha_two_code;
	}

	
}