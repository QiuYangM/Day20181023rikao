package soexample.umeng.com.day20181023rikao.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.activity.MainActivity;
import soexample.umeng.com.day20181023rikao.fragment.HomeFragment;
import soexample.umeng.com.day20181023rikao.fragment.ListFragment;
import soexample.umeng.com.day20181023rikao.fragment.MyFragment;
import soexample.umeng.com.day20181023rikao.fragment.ShopCarFragment;
import soexample.umeng.com.day20181023rikao.mvp.view.AppCreateimp;
import soexample.umeng.com.day20181023rikao.tabview.TabView;
import soexample.umeng.com.day20181023rikao.tabview.TabViewChild;

public class MainActivityPresenter extends AppCreateimp {
    private Context context;
    private List<TabViewChild> tabViewChildren = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initiData() {
        super.initiData();
        TabView tabView = get(R.id.tab_view);
        //第一个图片为选中 第二个为不选中
        TabViewChild tabViewChild01 = new TabViewChild(R.drawable.index_yes, R.drawable.index_no, "首页", new HomeFragment());
        TabViewChild tabViewChild02 = new TabViewChild(R.drawable.list_yes, R.drawable.list_no, "列表", new ListFragment());
        TabViewChild tabViewChild03 = new TabViewChild(R.drawable.car_yes, R.drawable.car_no, "购物车", new ShopCarFragment());
        TabViewChild tabViewChild04 = new TabViewChild(R.drawable.me_yes, R.drawable.me_no, "我的", new MyFragment());
        tabViewChildren.add(tabViewChild01);
        tabViewChildren.add(tabViewChild02);
        tabViewChildren.add(tabViewChild03);
        tabViewChildren.add(tabViewChild04);
        tabView.setTabViewChild(tabViewChildren,((MainActivity)context).getSupportFragmentManager());
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.context = context;
    }
}
