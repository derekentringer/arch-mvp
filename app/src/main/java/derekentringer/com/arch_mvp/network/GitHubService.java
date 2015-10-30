package derekentringer.com.arch_mvp.network;

import java.util.List;

import derekentringer.com.arch_mvp.model.Repository;
import derekentringer.com.arch_mvp.model.User;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Url;
import rx.Observable;

public interface GitHubService {

	@GET("users/{username}/repos")
	Observable<List<Repository>> publicRepositories(@Path("username") String username);

	@GET
	Observable<User> userFromUrl(@Url String userUrl);

	class Factory {
		public static GitHubService create() {
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl("https://api.github.com/")
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
					.build();
			return retrofit.create(GitHubService.class);
		}
	}
}