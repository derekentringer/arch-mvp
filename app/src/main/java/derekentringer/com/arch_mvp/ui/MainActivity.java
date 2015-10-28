package derekentringer.com.arch_mvp.ui;

import android.app.ListActivity;
import android.os.Bundle;

import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.network.StackOverflowAPI;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends ListActivity implements Callback<StackOverflowAPI> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onResponse(Response<StackOverflowAPI> response, Retrofit retrofit) {

	}

	@Override
	public void onFailure(Throwable t) {

	}

}