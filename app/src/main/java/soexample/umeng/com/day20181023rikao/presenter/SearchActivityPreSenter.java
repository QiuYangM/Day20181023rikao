package soexample.umeng.com.day20181023rikao.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.activity.SearchActivity;
import soexample.umeng.com.day20181023rikao.cview.GoodsView;
import soexample.umeng.com.day20181023rikao.model.HootBean;
import soexample.umeng.com.day20181023rikao.mvp.view.AppCreateimp;
import soexample.umeng.com.day20181023rikao.net.OkHttps;

public class SearchActivityPreSenter extends AppCreateimp implements View.OnClickListener {
    private Context context;
    private EditText mEditText;
    private GoodsView topGoodsView, btmGoodsView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initiData() {
        super.initiData();
        mEditText = get(R.id.ed_text);
        setOnClick(this, R.id.bt_search, R.id.bt_back);

        topGoodsView = (GoodsView) get(R.id.good_view_top);
        btmGoodsView = (GoodsView) get(R.id.good_view_btm);

    }


    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    private List<HootBean.DatasBean> datasBeans = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_search:

                String trim = mEditText.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Toast.makeText(context, "请输入您要搜索的数据", Toast.LENGTH_LONG).show();
                } else {
                    HootBean.DatasBean listBean = new HootBean.DatasBean();
                    listBean.setName(trim);
                    datasBeans.add(listBean);
                    btmGoodsView.setList(datasBeans);
                }
                break;
            case R.id.bt_back:
                ((SearchActivity) context).finish();
                break;
        }
    }
}
