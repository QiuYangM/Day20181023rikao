package soexample.umeng.com.day20181023rikao.fragment;

import soexample.umeng.com.day20181023rikao.mvp.prisenter.BaseFragmentPreseter;
import soexample.umeng.com.day20181023rikao.presenter.HomeFragmentPresenter;
import soexample.umeng.com.day20181023rikao.presenter.MyFragmentPresenter;

public class MyFragment extends BaseFragmentPreseter<MyFragmentPresenter> {


    @Override
    public Class<MyFragmentPresenter> getAppCreate() {
        return MyFragmentPresenter.class;
    }
}
