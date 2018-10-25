package soexample.umeng.com.day20181023rikao.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.activity.SearchActivity;
import soexample.umeng.com.day20181023rikao.adpter.HomeGridAdpter;
import soexample.umeng.com.day20181023rikao.adpter.HomeViewPager;
import soexample.umeng.com.day20181023rikao.adpter.ShopListItemAdpter;
import soexample.umeng.com.day20181023rikao.fragment.HomeFragment;
import soexample.umeng.com.day20181023rikao.model.BGABean;
import soexample.umeng.com.day20181023rikao.model.LeftBean;
import soexample.umeng.com.day20181023rikao.model.ShopCarBean;
import soexample.umeng.com.day20181023rikao.mvp.view.AppCreateimp;
import soexample.umeng.com.day20181023rikao.net.Http;

public class HomeFragmentPresenter extends AppCreateimp implements View.OnClickListener {
    private Context context;
    private BGABanner mBGABanner;
    private List<String> listImg = new ArrayList<>();
    private List<String> listText = new ArrayList<>();
    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private int allPage = 0;//全部页数
    private List<LeftBean.DataBean> listBeanOne = new ArrayList<>();
    private List<LeftBean.DataBean> listBeanTwo = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initiData() {
        super.initiData();
        mBGABanner = get(R.id.home_banner);
        mViewPager = get(R.id.view_pager);
        mRecyclerView = get(R.id.home_recy_button);
        setOnClick(this, R.id.home_btimg_left, R.id.home_btimg_right, R.id.home_edix);
        getString(Http.luoUrl, 0);
        getString(Http.ListLfteUrl, 1);
        getString(Http.HTTP_SHOP_LIST, 2);
    }

    @Override
    public void successString(int type, String data) {
        super.successString(type, data);
        switch (type) {
            case 0://轮播图
                BGABean bgaBean = new Gson().fromJson(data, BGABean.class);
                List<BGABean.DataBean> data1 = bgaBean.getData();
                //循环获取data里面的信息
                for (int i = 0; i < data1.size(); i++) {
                    listImg.add(data1.get(i).getIcon());
                    listText.add(data1.get(i).getTitle());
                }
                //给banner设置数据
                mBGABanner.setData(listImg, listText);
                mBGABanner.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                        Glide.with(context).load(listImg.get(position).replace("https", "http")).into((ImageView) itemView);
                    }
                });
                break;
            case 1://gridview网络解析
                LeftBean leftBean = new Gson().fromJson(data, LeftBean.class);
                int size = leftBean.getData().size();
                int num = size / 10;
                int num2 = size & 10;
                if (num2 != 0) {
                    num = num + 1;
                }
                allPage = num;
                listBeanOne.clear();
                listBeanTwo.clear();
                List<LeftBean.DataBean> data2 = leftBean.getData();
                for (int i = 0; i < data2.size(); i++) {
                    LeftBean.DataBean dataBean = data2.get(i);
                    if (i < 10) {
                        listBeanOne.add(dataBean);
                    } else {
                        listBeanTwo.add(dataBean);
                    }
                    HomeViewPager viewPager = new HomeViewPager(context, allPage, listBeanOne, listBeanTwo);
                    mViewPager.setAdapter(viewPager);
                }
                break;
            case 2://瀑布流
                ShopCarBean shopCarBean = new Gson().fromJson(data, ShopCarBean.class);
                List<ShopCarBean.DataBean> data3 = shopCarBean.getData();
                //设置适配器
                ShopListItemAdpter shopListItemAdpter = new ShopListItemAdpter(context, 1);
                shopListItemAdpter.setList(data3);
                mRecyclerView.setAdapter(shopListItemAdpter);
                //设置管理器
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_btimg_left://左边的二维码扫描
                context.startActivity(new Intent(context, CaptureActivity.class));
                break;
            case R.id.home_btimg_right://右边的消息
                tost("亲（@-_-@）您没有任何消息");
                break;
            case R.id.home_edix:
                context.startActivity(new Intent(context, SearchActivity.class));
                break;
        }

    }

    //吐司
    private void tost(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
//
}
