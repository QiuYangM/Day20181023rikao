package soexample.umeng.com.day20181023rikao.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.adpter.ListLeftAdpter;
import soexample.umeng.com.day20181023rikao.adpter.ListRightAdpter;
import soexample.umeng.com.day20181023rikao.model.LeftBean;
import soexample.umeng.com.day20181023rikao.model.RightBean;
import soexample.umeng.com.day20181023rikao.mvp.view.AppCreateimp;
import soexample.umeng.com.day20181023rikao.net.Http;

/**
 * 列表页面页面
 * leftlist   类型
 * rightlist  类型 信息
 */
public class ListFragmentPresenter extends AppCreateimp {
    private Context context;
    private ListView mListViewLeft;
    private RecyclerView mRecyclerViewRight;

    @Override
    public int getLayoutId() {
        return R.layout.list_fragment;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initiData() {
        super.initiData();
        mListViewLeft = get(R.id.list_view_left);
        mRecyclerViewRight = get(R.id.list_right);
        getString(Http.ListLfteUrl, 0);
        getString(Http.ListRightUrl + "?cid=" + 1, 1);
    }

    @Override
    public void successString(final int type, final String data) {
        super.successString(type, data);
        switch (type) {
            case 0://左边的list
                LeftBean leftBean = new Gson().fromJson(data, LeftBean.class);
                final List<LeftBean.DataBean> data1 = leftBean.getData();
                final ListLeftAdpter listLeftAdpter = new ListLeftAdpter(context, data1);
                mListViewLeft.setAdapter(listLeftAdpter);
                mListViewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    //点击条目获取当前id
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long pos) {
                        int id = data1.get(position).getCid();
                        getString(Http.ListRightUrl + "?cid=" + id, 1);
                    }
                });
                break;
            case 1://右边的list
                RightBean gson = new Gson().fromJson(data, RightBean.class);
                List<RightBean.DataBean> data2 = gson.getData();
                //适配器
                ListRightAdpter listRightAdpter = new ListRightAdpter(context);
                listRightAdpter.setList(data2);
                //定义网格管理器
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerViewRight.setLayoutManager(manager);
                mRecyclerViewRight.setAdapter(listRightAdpter);
                break;
        }
    }


}
