package derekentringer.com.arch_mvp.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity {

    private V viewBinding;

    protected V getViewBinding() {
        return viewBinding;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        this.viewBinding = DataBindingUtil.setContentView(this, layoutResID);
    }

}