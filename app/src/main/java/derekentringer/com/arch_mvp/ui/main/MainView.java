package derekentringer.com.arch_mvp.ui.main;

import java.util.List;

import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.ui.BaseView;

public interface MainView extends BaseView {

	void showRepositories(List<Repository> repositories);
	void showMessage(int stringId);
	void showProgressIndicator();

}