package soexample.umeng.com.day20181023rikao.activity;

import soexample.umeng.com.day20181023rikao.mvp.prisenter.BaseActivityPreseter;
import soexample.umeng.com.day20181023rikao.presenter.SearchActivityPreSenter;

public class SearchActivity extends BaseActivityPreseter<SearchActivityPreSenter> {


    @Override
    public Class<SearchActivityPreSenter> getAppCreate() {
        return SearchActivityPreSenter.class;
    }
}
