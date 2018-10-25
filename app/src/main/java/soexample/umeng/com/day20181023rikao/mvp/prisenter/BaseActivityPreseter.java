package soexample.umeng.com.day20181023rikao.mvp.prisenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import soexample.umeng.com.day20181023rikao.mvp.view.AppCreate;

public abstract class BaseActivityPreseter<T extends AppCreate> extends AppCompatActivity {

    private T appcreate;

    public abstract Class<T> getAppCreate();

    public BaseActivityPreseter() {
        try {
            appcreate = getAppCreate().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appcreate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(appcreate.rootView());
        appcreate.initContext(this);
        appcreate.initiData();
    }
}
