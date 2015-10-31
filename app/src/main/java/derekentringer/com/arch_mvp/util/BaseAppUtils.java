package derekentringer.com.arch_mvp.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class BaseAppUtils {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager mgr = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

}