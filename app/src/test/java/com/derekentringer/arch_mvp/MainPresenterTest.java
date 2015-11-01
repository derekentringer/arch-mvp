package com.derekentringer.arch_mvp;

import com.derekentringer.arch_mvp.model.RepositoryMockModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collections;
import java.util.List;

import derekentringer.com.arch_mvp.App;
import derekentringer.com.arch_mvp.BuildConfig;
import derekentringer.com.arch_mvp.R;
import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.network.RetroFitClient;
import derekentringer.com.arch_mvp.ui.main.MainPresenter;
import derekentringer.com.arch_mvp.ui.main.MainView;
import retrofit.HttpException;
import retrofit.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(manifest = "app/src/main/AndroidManifest.xml", constants = BuildConfig.class, sdk = 21)
public class MainPresenterTest {

    MainPresenter mainPresenter;
    MainView mainView;
    RetroFitClient retroFitClient;

    @Before
    public void setUp() {
        App app = (App) RuntimeEnvironment.application;
        retroFitClient = mock(RetroFitClient.class);
        app.setRetroFitClient(retroFitClient);
        app.setDefaultSubscriberScheduler(Schedulers.immediate());
        mainPresenter = new MainPresenter();
        mainView = mock(MainView.class);
        when(mainView.getContext()).thenReturn(app);
        mainPresenter.attachView(mainView);
    }

    @After
    public void tearDown() {
        mainPresenter.detachView();
    }

    @Test
    public void loadRepositoriesShowMessageDefaultError() {
        String username = "username";
        when(retroFitClient.publicRepositories(username))
                .thenReturn(Observable.<List<Repository>>error(new RuntimeException("error")));
        mainPresenter.loadRepositories(username);
        verify(mainView).showProgressIndicator();
        verify(mainView).showMessage(R.string.error_loading_repos);
    }

    @Test
    public void loadRepositoriesShowMessageUserNotFound() {
        String username = "username";
        when(retroFitClient.publicRepositories(username))
                .thenReturn(Observable.<List<Repository>>error(new HttpException(Response.error(404, null))));
        mainPresenter.loadRepositories(username);
        verify(mainView).showProgressIndicator();
        verify(mainView).showMessage(R.string.error_username_not_found);
    }

    @Test
    public void loadRepositoriesShowMessageNoPublicRepos() {
        String username = "username";
        when(retroFitClient.publicRepositories(username))
                .thenReturn(Observable.just(Collections.<Repository>emptyList()));
        mainPresenter.loadRepositories(username);
        verify(mainView).showProgressIndicator();
        verify(mainView).showMessage(R.string.text_empty_repos);
    }


    @Test
    public void loadPublicRepositoriesShowRepositories() {
        String username = "username";
        List<Repository> repositories = RepositoryMockModel.newListOfRepositories(10);
        when(retroFitClient.publicRepositories(username))
                .thenReturn(Observable.just(repositories));
        mainPresenter.loadRepositories(username);
        verify(mainView).showProgressIndicator();
        verify(mainView).showRepositories(repositories);
    }

}
