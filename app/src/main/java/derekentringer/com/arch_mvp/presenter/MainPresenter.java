package derekentringer.com.arch_mvp.presenter;

import java.util.List;

import derekentringer.com.arch_mvp.App;
import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.network.ErrorCode;
import derekentringer.com.arch_mvp.network.RetroFitClient;
import derekentringer.com.arch_mvp.view.MainView;
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

        App app = App.getContext(mainView.getContext());
        subscription = RetroFitClient.retroFitApi.publicRepositories(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.getDefaultSubscriberScheduler())
                .subscribe(new Subscriber<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                        if (!repositories.isEmpty()) {
                            mainView.showRepositories(repositories);
                        }
                        else {
                            mainView.showMessage(R.string.text_empty_repos);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        if (ErrorCode.is404(error)) {
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

}