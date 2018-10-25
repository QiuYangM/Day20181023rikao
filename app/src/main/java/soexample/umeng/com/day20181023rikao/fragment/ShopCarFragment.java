package soexample.umeng.com.day20181023rikao.fragment;

import soexample.umeng.com.day20181023rikao.mvp.prisenter.BaseFragmentPreseter;
import soexample.umeng.com.day20181023rikao.presenter.HomeFragmentPresenter;
import soexample.umeng.com.day20181023rikao.presenter.ShopCarFragmentPresenter;

public class ShopCarFragment extends BaseFragmentPreseter<ShopCarFragmentPresenter> {


    @Override
    public Class<ShopCarFragmentPresenter> getAppCreate() {
        return ShopCarFragmentPresenter.class;
    }
}
