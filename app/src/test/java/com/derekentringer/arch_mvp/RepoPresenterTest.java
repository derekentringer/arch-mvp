package com.derekentringer.arch_mvp;

import com.derekentringer.arch_mvp.model.RepositoryMockModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import derekentringer.com.arch_mvp.App;
import derekentringer.com.arch_mvp.BuildConfig;
import derekentringer.com.arch_mvp.model.User;
import derekentringer.com.arch_mvp.network.RetroFitClient;
import derekentringer.com.arch_mvp.ui.repo.RepoPresenter;
import derekentringer.com.arch_mvp.ui.repo.RepoView;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(manifest = "app/src/main/AndroidManifest.xml", constants = BuildConfig.class, sdk = 21)
public class RepoPresenterTest {

    RepoPresenter repoPresenter;
    RepoView repoView;
    RetroFitClient retroFitClient;

    @Before
    public void setup() {
        App app = (App) RuntimeEnvironment.application;
        retroFitClient = mock(RetroFitClient.class);
        app.setRetroFitClient(retroFitClient);
        app.setDefaultSubscriberScheduler(Schedulers.immediate());
        repoPresenter = new RepoPresenter();
        repoView = mock(RepoView.class);
        when(repoView.getContext()).thenReturn(app);
        repoPresenter.attachView(repoView);
    }

    @After
    public void tearDown() {
        repoPresenter.detachView();
    }

    @Test
    public void loadUserShowUser() {
        User owner = RepositoryMockModel.newUser("user");
        String userUrl = "http://user.com/more";
        when(retroFitClient.userFromUrl(userUrl))
                .thenReturn(Observable.just(owner));
        repoPresenter.loadUser(userUrl);
        verify(repoView).showUser(owner);
    }

}