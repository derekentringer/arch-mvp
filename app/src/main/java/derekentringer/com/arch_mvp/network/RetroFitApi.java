package derekentringer.com.arch_mvp.network;

import java.util.List;

import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.model.User;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Url;
import rx.Observable;

public interface RetroFitApi {

    @GET("users/{username}/repos")
    Observable<List<Repository>> publicRepositories(@Path("username") String username);

    @GET
    Observable<User> userFromUrl(@Url String userUrl);

}