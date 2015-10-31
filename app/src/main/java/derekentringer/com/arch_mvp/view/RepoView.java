package derekentringer.com.arch_mvp.view;

import derekentringer.com.arch_mvp.model.User;

public interface RepoView extends BaseView {

    void showUser(final User user);

}