package soexample.umeng.com.day20181023rikao.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.model.LeftBean;

public class HomeViewPager extends PagerAdapter {
    private int allPage = 0;//全部页数
    private List<LeftBean.DataBean> listBeanOne;
    private List<LeftBean.DataBean> listBeanTwo;
    private Context context;

    public HomeViewPager(Context context, int allPage, List<LeftBean.DataBean> listBeanOne, List<LeftBean.DataBean> listBeanTwo) {
        this.allPage = allPage;
        this.listBeanOne = listBeanOne;
        this.listBeanTwo = listBeanTwo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return allPage;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = View.inflate(context, R.layout.layout_viewpager_item, null);
        RecyclerView mRecyclerView = inflate.findViewById(R.id.home_recyview);
        createRecyclerAdapter(mRecyclerView, position);
        container.addView(inflate);
        return inflate;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private void createRecyclerAdapter(RecyclerView mRecyclerView, int position) {

        LinearLayoutManager manager = new GridLayoutManager(context, 4);
        mRecyclerView.setLayoutManager(manager);
        HomeGridAdpter homeGridAdpter = null;
        if (position == 0) {
            homeGridAdpter = new HomeGridAdpter(context,listBeanOne);
        }else {
            homeGridAdpter = new HomeGridAdpter(context,listBeanTwo);
        }
        mRecyclerView.setAdapter(homeGridAdpter);
    }
}
