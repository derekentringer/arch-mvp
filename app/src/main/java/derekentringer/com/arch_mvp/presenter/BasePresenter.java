package derekentringer.com.arch_mvp.presenter;

public interface BasePresenter<V> {
	void attachView(V view);
	void detachView();
}