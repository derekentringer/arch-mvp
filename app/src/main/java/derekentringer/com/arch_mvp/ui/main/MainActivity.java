package derekentringer.com.arch_mvp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.presenter.MainPresenter;
import derekentringer.com.arch_mvp.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

	private static final String TAG = MainActivity.class.getSimpleName();

	private MainPresenter mainPresenter;

	private ProgressBar progressBar;
	private TextView infoTextView;
	private Toolbar toolbar;
	private RecyclerView reposRecyclerView;
	private EditText editTextUsername;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mainPresenter = new MainPresenter();
		mainPresenter.attachView(this);

		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) findViewById(R.id.progress);
		infoTextView = (TextView) findViewById(R.id.text_info);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		reposRecyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);
		setupRecyclerView(reposRecyclerView);
	}

	@Override
	public void showRepositories(List<Repository> repositories) {
		RepositoryAdapter adapter = (RepositoryAdapter) reposRecyclerView.getAdapter();
		adapter.setRepositories(repositories);
		adapter.notifyDataSetChanged();

		reposRecyclerView.requestFocus();
		hideSoftKeyboard();

		progressBar.setVisibility(View.INVISIBLE);
		infoTextView.setVisibility(View.INVISIBLE);

		reposRecyclerView.setVisibility(View.VISIBLE);
		setupRecyclerView(reposRecyclerView);

		editTextUsername = (EditText) findViewById(R.id.edit_text_username);
		editTextUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					mainPresenter.loadRepositories(editTextUsername.getText().toString());
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public void showMessage(int stringId) {
		progressBar.setVisibility(View.INVISIBLE);
		infoTextView.setVisibility(View.VISIBLE);
		reposRecyclerView.setVisibility(View.INVISIBLE);
		infoTextView.setText(getString(stringId));
	}

	@Override
	public void showProgressIndicator() {
		progressBar.setVisibility(View.VISIBLE);
		infoTextView.setVisibility(View.INVISIBLE);
		reposRecyclerView.setVisibility(View.INVISIBLE);
	}

	@Override
	public Context getContext() {
		return this;
	}

	private void setupRecyclerView(RecyclerView recyclerView) {
		RepositoryAdapter adapter = new RepositoryAdapter();
		adapter.setCallback(new RepositoryAdapter.Callback() {
			@Override
			public void onItemClick(Repository repository) {
				//startActivity(RepositoryActivity.newIntent(MainActivity.this, repository));
			}
		});
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	@Override
	protected void onDestroy() {
		mainPresenter.detachView();
		super.onDestroy();
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);
	}

}