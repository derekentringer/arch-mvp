package derekentringer.com.arch_mvp;

import android.app.Application;
import android.content.Context;

import derekentringer.com.arch_mvp.network.RetroFitClient;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class App extends Application {

    private RetroFitClient retroFitClient;
	private Scheduler defaultSubscriberScheduler;

    @Override
    public void onCreate() {
        super.onCreate();
    }

	public static App getContext(Context context) {
		return (App) context.getApplicationContext();
	}

    public RetroFitClient getRetroFitClient() {
        if (retroFitClient == null) {
            retroFitClient = RetroFitClient.Factory.create();
        }
        return retroFitClient;
    }

    public void setRetroFitClient(RetroFitClient retroFitClient) {
        this.retroFitClient = retroFitClient;
    }

	public Scheduler getDefaultSubscriberScheduler() {
		if (defaultSubscriberScheduler == null) {
			defaultSubscriberScheduler = Schedulers.io();
		}
		return defaultSubscriberScheduler;
	}

    public void setDefaultSubscriberScheduler(Scheduler scheduler) {
        this.defaultSubscriberScheduler = scheduler;
    }

}