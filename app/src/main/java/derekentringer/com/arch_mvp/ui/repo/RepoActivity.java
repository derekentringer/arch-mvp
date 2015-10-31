package derekentringer.com.arch_mvp.ui.repo;

import android.content.Context;
import android.os.Bundle;

import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.databinding.RepoActivityBinding;
import derekentringer.com.arch_mvp.ui.BaseActivity;
import derekentringer.com.arch_mvp.view.RepoView;

public class RepoActivity extends BaseActivity<RepoActivityBinding> implements RepoView {

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