package derekentringer.com.arch_mvp.ui.repo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.databinding.RepoActivityBinding;
import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.ui.BaseActivity;
import derekentringer.com.arch_mvp.view.RepoView;

public class RepoActivity extends BaseActivity<RepoActivityBinding> implements RepoView {

    private static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";

    public static Intent newInstance(Context context, Repository repository) {
        Intent intent = new Intent(context, RepoActivity.class);
        intent.putExtra(EXTRA_REPOSITORY, repository);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
    }

    @Override
    public Context getContext() {
        return this;
    }

}