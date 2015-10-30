package derekentringer.com.arch_mvp.presenter;

import android.util.Log;

import java.util.List;

import derekentringer.com.arch_mvp.App;
import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.network.GitHubService;
import derekentringer.com.arch_mvp.view.MainView;
import retrofit.HttpException;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainPresenter implements BasePresenter<MainView> {

	public static final String TAG = MainPresenter.class.getSimpleName();

	private MainView mainView;
	private Subscription subscription;
	private List<Repository> repositories;

	@Override
	public void attachView(MainView view) {
		this.mainView = view;
	}

	@Override
	public void detachView() {
		this.mainView = null;
		if (subscription != null) {
			subscription.unsubscribe();
		}
	}

	public void loadRepositories(String username) {
		if (username.isEmpty()) {
			return;
		}

		mainView.showProgressIndicator();

		if (subscription != null) {
			subscription.unsubscribe();
		}

		App application = App.get(mainView.getContext());
		GitHubService gitHubService = application.getGitHubService();

		subscription = gitHubService.publicRepositories(username)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(application.defaultSubscriberScheduler())
				.subscribe(new Subscriber<List<Repository>>() {
					@Override
					public void onCompleted() {
						Log.i(TAG, "Repos loaded " + repositories);
						if (!repositories.isEmpty()) {
							mainView.showRepositories(repositories);
						}
						else {
							mainView.showMessage(R.string.text_empty_repos);
						}
					}

					@Override
					public void onError(Throwable error) {
						Log.e(TAG, "Error loading GitHub repos ", error);
						if (isHttp404(error)) {
							mainView.showMessage(R.string.error_username_not_found);
						}
						else {
							mainView.showMessage(R.string.error_loading_repos);
						}
					}

					@Override
					public void onNext(List<Repository> repositories) {
						MainPresenter.this.repositories = repositories;
					}
				});

	}

	private static boolean isHttp404(Throwable error) {
		return error instanceof HttpException && ((HttpException) error).code() == 404;
	}

}