package derekentringer.com.arch_mvp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.databinding.MainActivityBinding;
import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.ui.BaseActivity;
import derekentringer.com.arch_mvp.ui.repo.RepoActivity;
import derekentringer.com.arch_mvp.util.BaseAppUtils;

public class MainActivity extends BaseActivity<MainActivityBinding> implements MainView {

	private static final String TAG = MainActivity.class.getSimpleName();

	private MainPresenter mainPresenter;

    //region lifecycle
    @Override
    public Context getContext() {
        return this;
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mainPresenter = new MainPresenter();
		mainPresenter.attachView(this);

		setContentView(R.layout.activity_main);

        TextView view = (TextView) findViewById(R.id.edit_text_username);
        view.setOnClickListener(e -> Toast.makeText(this, "enter a username", Toast.LENGTH_LONG).show());

		initViews();
	}

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }
    //endregion

    private void initViews() {
        setSupportActionBar(getViewBinding().toolbar);
        setupRecyclerView(getViewBinding().reposRecyclerView);
        getViewBinding().editTextUsername.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 3) {
                    mainPresenter.loadRepositories(editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        RepositoryAdapter adapter = new RepositoryAdapter();
        adapter.setCallback(new RepositoryAdapter.Callback() {
            @Override
            public void onItemClick(Repository repository) {
                startActivity(RepoActivity.newInstance(MainActivity.this, repository));
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //region view overrides
    @Override
    public void showRepositories(List<Repository> repositories) {
        RepositoryAdapter adapter = (RepositoryAdapter) getViewBinding().reposRecyclerView.getAdapter();
        adapter.setRepositories(repositories);
        adapter.notifyDataSetChanged();
        getViewBinding().reposRecyclerView.requestFocus();

        BaseAppUtils.hideKeyboard(this);

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
    //endregion

}