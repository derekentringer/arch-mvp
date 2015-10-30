package derekentringer.com.arch_mvp.view;

import java.util.List;

import derekentringer.com.arch_mvp.model.Repository;

public interface MainView extends BaseView {

	void showRepositories(List<Repository> repositories);
	void showMessage(int stringId);
	void showProgressIndicator();

}