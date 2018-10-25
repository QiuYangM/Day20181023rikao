package soexample.umeng.com.day20181023rikao.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.mvp.prisenter.BaseFragmentPreseter;
import soexample.umeng.com.day20181023rikao.presenter.HomeFragmentPresenter;

public class HomeFragment extends BaseFragmentPreseter<HomeFragmentPresenter> {


    @Override
    public Class<HomeFragmentPresenter> getAppCreate() {
        return HomeFragmentPresenter.class;
    }
}
