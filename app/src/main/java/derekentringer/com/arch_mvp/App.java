package derekentringer.com.arch_mvp;

import android.app.Application;
import android.content.Context;

import derekentringer.com.arch_mvp.network.GitHubService;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class App extends Application {

	private GitHubService gitHubService;
	private Scheduler defaultSubscriberScheduler;

	public static App get(Context context) {
		return (App) context.getApplicationContext();
	}

	public GitHubService getGitHubService() {
		if (gitHubService == null) {
			gitHubService = GitHubService.Factory.create();
		}
		return gitHubService;
	}

	public void setGitHubService(GitHubService gitHubService) {
		this.gitHubService = gitHubService;
	}

	public Scheduler defaultSubscriberScheduler() {
		if (defaultSubscriberScheduler == null) {
			defaultSubscriberScheduler = Schedulers.io();
		}
		return defaultSubscriberScheduler;
	}

	public void setDefaultSubscriberScheduler(Scheduler scheduler) {
		this.defaultSubscriberScheduler = scheduler;
	}

}