package derekentringer.com.arch_mvp.ui;

public interface BasePresenter<V> {

	void attachView(V view);
	void detachView();

}