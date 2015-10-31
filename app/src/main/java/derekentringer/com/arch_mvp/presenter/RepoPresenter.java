package derekentringer.com.arch_mvp.presenter;

import derekentringer.com.arch_mvp.App;
import derekentringer.com.arch_mvp.model.User;
import derekentringer.com.arch_mvp.network.RetroFitClient;
import derekentringer.com.arch_mvp.view.RepoView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RepoPresenter implements BasePresenter<RepoView> {

    public static final String TAG = RepoPresenter.class.getSimpleName();

    private RepoView repoView;
    private Subscription subscription;


    @Override
    public void attachView(RepoView view) {
        this.repoView = view;
    }

    @Override
    public void detachView() {
        this.repoView = null;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public void loadUser(String userUrl) {
        App app = App.getContext(repoView.getContext());
        subscription = RetroFitClient.retroFitApi.userFromUrl(userUrl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.getDefaultSubscriberScheduler())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        repoView.showUser(user);
                    }
                });
    }

}