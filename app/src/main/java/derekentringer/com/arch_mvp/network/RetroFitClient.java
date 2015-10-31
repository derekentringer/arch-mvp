package derekentringer.com.arch_mvp.network;

import android.content.Context;

import derekentringer.com.arch_mvp.settings.AppSettings;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class RetroFitClient {

    private static RetroFitClient retroFitClient = new RetroFitClient();
    public static RetroFitApi retroFitApi;

    protected RetroFitClient() {
    }

    public static RetroFitClient getInstance() {
        if (retroFitClient == null) {
            retroFitClient = new RetroFitClient();
        }
        return retroFitClient;
    }

    public void initialize(Context context) {
        retroFitClient = this;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppSettings.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retroFitApi = retrofit.create(RetroFitApi.class);
    }

}