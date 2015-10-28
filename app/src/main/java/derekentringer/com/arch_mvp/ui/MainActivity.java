package derekentringer.com.arch_mvp.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import derekentringer.com.arch_mvp.model.QuestionModel;
import derekentringer.com.arch_mvp.model.QuestionsModel;
import derekentringer.com.arch_mvp.network.RetrofitApiService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends ListActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		requestWindowFeature(Window.FEATURE_PROGRESS);

		final ArrayAdapter<QuestionModel> arrayAdapter =
				new ArrayAdapter<QuestionModel>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1,
						new ArrayList<QuestionModel>());

		setListAdapter(arrayAdapter);
		setProgressBarIndeterminateVisibility(true);
		setProgressBarVisibility(true);

		makeTheCall();
	}

	private void makeTheCall() {
		setProgressBarIndeterminateVisibility(true);
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.stackexchange.com")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		RetrofitApiService retrofitApiService = retrofit.create(RetrofitApiService.class);
		Call<QuestionsModel> call = retrofitApiService.loadQuestions("android");
		call.enqueue(new Callback<QuestionsModel>() {
			@Override
			public void onResponse(Response<QuestionsModel> response, Retrofit retrofit) {
				setProgressBarIndeterminateVisibility(false);

				List<QuestionModel> questions = response.body().getQuestionModelList();

				ArrayAdapter<QuestionModel> adapter = (ArrayAdapter<QuestionModel>) getListAdapter();
				adapter.clear();

				for (QuestionModel question: questions) {
					adapter.add(question);
				}
			}

			@Override
			public void onFailure(Throwable t) {
				Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
			}
		});
	}

}