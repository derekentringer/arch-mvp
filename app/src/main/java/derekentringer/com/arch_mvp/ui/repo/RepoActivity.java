package derekentringer.com.arch_mvp.ui.repo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.squareup.picasso.Picasso;

import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.databinding.RepoActivityBinding;
import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.model.User;
import derekentringer.com.arch_mvp.presenter.RepoPresenter;
import derekentringer.com.arch_mvp.ui.BaseActivity;
import derekentringer.com.arch_mvp.view.RepoView;

public class RepoActivity extends BaseActivity<RepoActivityBinding> implements RepoView {

    private static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";

    private RepoPresenter repoPresenter;

    public static Intent newInstance(Context context, Repository repository) {
        Intent intent = new Intent(context, RepoActivity.class);
        intent.putExtra(EXTRA_REPOSITORY, repository);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repoPresenter = new RepoPresenter();
        repoPresenter.attachView(this);

        setContentView(R.layout.activity_repo);

        initViews();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showUser(final User user) {
        getViewBinding().textOwnerName.setText(user.getName());
        getViewBinding().textOwnerEmail.setText(user.getEmail());
        getViewBinding().textOwnerEmail.setVisibility(user.hasEmail() ? View.VISIBLE : View.GONE);
        getViewBinding().textOwnerLocation.setText(user.getLocation());
        getViewBinding().textOwnerLocation.setVisibility(user.hasLocation() ? View.VISIBLE : View.GONE);
        getViewBinding().layoutOwner.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        repoPresenter.detachView();
        super.onDestroy();
    }

    private void initViews() {
        Repository repository = getIntent().getParcelableExtra(EXTRA_REPOSITORY);

        setSupportActionBar(getViewBinding().toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        showRepoInfo(repository);
        repoPresenter.loadUser(repository.getOwner().getUrl());
    }

    private void showRepoInfo(final Repository repository) {
        setTitle(repository.getName());
        getViewBinding().textRepoDescription.setText(repository.getDescription());
        getViewBinding().textHomepage.setText(repository.getHomepage());
        getViewBinding().textHomepage.setVisibility(repository.hasHomePage() ? View.VISIBLE : View.GONE);
        getViewBinding().textLanguage.setText(getString(R.string.text_language, repository.getLanguage()));
        getViewBinding().textLanguage.setVisibility(repository.hasLanguage() ? View.VISIBLE : View.GONE);
        getViewBinding().textFork.setVisibility(repository.isFork() ? View.VISIBLE : View.GONE);

        Picasso.with(this)
                .load(repository.owner.getAvatarUrl())
                .placeholder(R.drawable.placeholder)
                .into(getViewBinding().imageOwner);
    }

}