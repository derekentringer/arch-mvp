package derekentringer.com.arch_mvp.network;

import derekentringer.com.arch_mvp.model.QuestionsModel;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface StackOverflowAPI {

	@GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
	Call<QuestionsModel> loadQuestions(@Query("tagged") String tags);

}