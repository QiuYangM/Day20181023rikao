package soexample.umeng.com.day20181023rikao.activity;

import soexample.umeng.com.day20181023rikao.mvp.prisenter.BaseActivityPreseter;
import soexample.umeng.com.day20181023rikao.presenter.MainActivityPresenter;

public class MainActivity extends BaseActivityPreseter<MainActivityPresenter> {


    @Override
    public Class<MainActivityPresenter> getAppCreate() {
        return MainActivityPresenter.class;
    }
}
