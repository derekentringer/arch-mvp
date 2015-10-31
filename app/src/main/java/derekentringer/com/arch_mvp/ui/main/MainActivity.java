package derekentringer.com.arch_mvp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.databinding.MainActivityBinding;
import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.presenter.MainPresenter;
import derekentringer.com.arch_mvp.ui.BaseActivity;
import derekentringer.com.arch_mvp.view.MainView;

public class MainActivity extends BaseActivity<MainActivityBinding> implements MainView {

	private static final String TAG = MainActivity.class.getSimpleName();

	private MainPresenter mainPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mainPresenter = new MainPresenter();
		mainPresenter.attachView(this);

		setContentView(R.layout.activity_main);
        
		initViews();
	}

    private void initViews() {
        setSupportActionBar(getViewBinding().toolbar);
        setupRecyclerView(getViewBinding().reposRecyclerView);
        getViewBinding().editTextUsername.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 3) {
                    mainPresenter.loadRepositories(editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    @Override
    public void showRepositories(List<Repository> repositories) {
        RepositoryAdapter adapter = (RepositoryAdapter) getViewBinding().reposRecyclerView.getAdapter();
        adapter.setRepositories(repositories);
        adapter.notifyDataSetChanged();
        getViewBinding().reposRecyclerView.requestFocus();

        hideSoftKeyboard();

        getViewBinding().progress.setVisibility(View.INVISIBLE);
        getViewBinding().textInfo.setVisibility(View.INVISIBLE);
        getViewBinding().reposRecyclerView.setVisibility(View.VISIBLE);
    }

	@Override
	public void showMessage(int stringId) {
		getViewBinding().progress.setVisibility(View.INVISIBLE);
		getViewBinding().textInfo.setVisibility(View.VISIBLE);
		getViewBinding().reposRecyclerView.setVisibility(View.INVISIBLE);
		getViewBinding().textInfo.setText(getString(stringId));
	}

	@Override
	public void showProgressIndicator() {
		getViewBinding().progress.setVisibility(View.VISIBLE);
		getViewBinding().textInfo.setVisibility(View.INVISIBLE);
		getViewBinding().reposRecyclerView.setVisibility(View.INVISIBLE);
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
		imm.hideSoftInputFromWindow(getViewBinding().editTextUsername.getWindowToken(), 0);
	}

}