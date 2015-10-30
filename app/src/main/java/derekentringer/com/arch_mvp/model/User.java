package derekentringer.com.arch_mvp.model;

import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("name")
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}