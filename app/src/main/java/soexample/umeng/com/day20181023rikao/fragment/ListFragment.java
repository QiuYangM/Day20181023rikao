package soexample.umeng.com.day20181023rikao.fragment;

import soexample.umeng.com.day20181023rikao.mvp.prisenter.BaseFragmentPreseter;
import soexample.umeng.com.day20181023rikao.presenter.HomeFragmentPresenter;
import soexample.umeng.com.day20181023rikao.presenter.ListFragmentPresenter;
import soexample.umeng.com.day20181023rikao.presenter.MyFragmentPresenter;

public class ListFragment extends BaseFragmentPreseter<ListFragmentPresenter> {


    @Override
    public Class<ListFragmentPresenter> getAppCreate() {
        return ListFragmentPresenter.class;
    }
}
