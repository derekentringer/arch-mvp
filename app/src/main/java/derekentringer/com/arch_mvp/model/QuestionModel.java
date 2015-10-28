package derekentringer.com.arch_mvp.model;

import com.google.gson.annotations.SerializedName;

public class QuestionModel {

	@SerializedName("title")
	private String mTitle;

	@SerializedName("link")
	private String mLink;

	public String getLink() {
		return mLink;
	}

	public String getTitle() {
		return mTitle;
	}

}