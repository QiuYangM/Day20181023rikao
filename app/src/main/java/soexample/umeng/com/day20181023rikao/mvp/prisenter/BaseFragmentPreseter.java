package soexample.umeng.com.day20181023rikao.mvp.prisenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soexample.umeng.com.day20181023rikao.mvp.view.AppCreate;

public abstract class BaseFragmentPreseter<T extends AppCreate> extends Fragment {

    private T appcreate;

    public abstract Class<T> getAppCreate();

    public BaseFragmentPreseter() {

        try {
            appcreate = getAppCreate().newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        appcreate.create(getLayoutInflater(), null, savedInstanceState);
        return appcreate.rootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appcreate.initContext(getActivity());
        appcreate.initiData();
    }
}
