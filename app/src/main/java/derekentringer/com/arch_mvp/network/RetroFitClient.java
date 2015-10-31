package derekentringer.com.arch_mvp.network;

import java.util.List;

import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.model.User;
import derekentringer.com.arch_mvp.settings.AppSettings;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Url;
import rx.Observable;

public interface RetroFitClient {

    @GET("users/{username}/repos")
    Observable<List<Repository>> publicRepositories(@Path("username") String username);

    @GET
    Observable<User> userFromUrl(@Url String userUrl);

    class Factory {
        public static RetroFitClient create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppSettings.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RetroFitClient.class);
        }
    }

}