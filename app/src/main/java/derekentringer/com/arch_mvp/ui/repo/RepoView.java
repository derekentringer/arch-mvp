package derekentringer.com.arch_mvp.ui.repo;

import derekentringer.com.arch_mvp.model.User;
import derekentringer.com.arch_mvp.ui.BaseView;

public interface RepoView extends BaseView {

    void showUser(final User user);

}