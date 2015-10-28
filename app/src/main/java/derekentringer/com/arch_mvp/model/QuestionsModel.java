package derekentringer.com.arch_mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionsModel {

	@SerializedName("items")
	private List<QuestionModel> mQuestionModelList;

	public List<QuestionModel> getQuestionModelList() {
		return mQuestionModelList;
	}

}