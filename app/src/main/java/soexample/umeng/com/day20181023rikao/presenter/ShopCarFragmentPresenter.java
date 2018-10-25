package soexample.umeng.com.day20181023rikao.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.adpter.ShopListItemAdpter;
import soexample.umeng.com.day20181023rikao.model.ShopCarBean;
import soexample.umeng.com.day20181023rikao.mvp.view.AppCreateimp;
import soexample.umeng.com.day20181023rikao.net.OkHttps;

public class ShopCarFragmentPresenter extends AppCreateimp implements View.OnClickListener {
    private Context context;
    private RecyclerView mRecyclerView;
    private ImageView imgs;
    private List<ShopCarBean.DataBean> list = new ArrayList<>();
    private TextView allPrice;
    private TextView allNum;

    @Override
    public int getLayoutId() {
        return R.layout.shopcar_fragment;
    }

    @Override
    public void initiData() {
        super.initiData();
        mRecyclerView = get(R.id.recy_view);
        allPrice = get(R.id.all_price);
        imgs = get(R.id.ivs_cricle);
        allNum = (TextView) get(R.id.sum_price_txt);
        getString("http://www.zhaoapi.cn/product/getCarts?uid=71", 0);
        setOnClick(this, R.id.ivs_cricle, R.id.layout_all);
    }

    private ShopListItemAdpter shopListItemAdpter;

    @Override
    public void successString(int type, String data) {
        super.successString(type, data);
        switch (type) {
            case 0:
                ShopCarBean shopCarBean = new Gson().fromJson(data, ShopCarBean.class);
                list = shopCarBean.getData();
                //设置适配器
                shopListItemAdpter = new ShopListItemAdpter(context, 0);
                shopListItemAdpter.setList(list);
                mRecyclerView.setAdapter(shopListItemAdpter);
                //设置管理器
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);
                //获取状态
                shopListItemAdpter.setState(new ShopListItemAdpter.ShopCallBack() {
                    @Override
                    public void callback(List<ShopCarBean.DataBean> list) {

                        double alprice = 0;
                        int num = 0;
                        int numAll = 0;
                        for (int i = 0; i < list.size(); i++) {
                            List<ShopCarBean.DataBean.ListBean> listAll = list.get(i).getList();
                            for (int j = 0; j < listAll.size(); j++) {
                                numAll = listAll.get(j).getNum();
                                if (listAll.get(j).isIscheck()) {//获取选中状态
                                    alprice = alprice + (listAll.get(j).getPrice() * listAll.get(j).getNum());
                                    num = num + listAll.get(j).getNum();
                                }
                            }

                        }
                        //判断
                        if (num < numAll) {
                            imgs.setImageResource(R.drawable.cricle_yes);
                            isCheck = false;
                        } else {
                            imgs.setImageResource(R.drawable.cricle_no);
                            isCheck = true;
                        }
                        allPrice.setText("合计:" + alprice);
                        allNum.setText("去结算(" + num + ")");
                    }
                });
                break;
        }
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    private boolean isCheck = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_all:
                if (isCheck) {
                    imgs.setImageResource(R.drawable.cricle_yes);
                    isCheck = false;
                    checks(true);
                } else {
                    imgs.setImageResource(R.drawable.cricle_no);
                    isCheck = true;
                    checks(false);
                }
                break;
        }
    }

    private void checks(boolean isc) {
        double alprice = 0;
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            List<ShopCarBean.DataBean.ListBean> list1
                    = list.get(i).getList();
            for (int j = 0; j < list1.size(); j++) {
                list1.get(j).setIscheck(isc);
                alprice = alprice + (list1.get(j).getPrice() * list1.get(j).getNum());
                num = num + list1.get(j).getNum();
            }
        }


        //如果被选中计算价钱总数
        if (isc) {
            allPrice.setText("合计:" + alprice);
            allNum.setText("去结算(" + num + ")");
        } else {
            allPrice.setText("合计:0.00");
            allNum.setText("去结算(0)");
        }
        shopListItemAdpter.notifyDataSetChanged();
    }
}
