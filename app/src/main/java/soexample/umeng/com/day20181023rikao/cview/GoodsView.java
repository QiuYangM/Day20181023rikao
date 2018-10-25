package soexample.umeng.com.day20181023rikao.cview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.model.HootBean;

/**
 * author:AbnerMing
 * date:2018/10/10
 * 流式布局
 */
public class GoodsView extends RelativeLayout {
    private View view;
    private LinearLayout mLyoutV;

    public GoodsView(Context context) {
        super(context);
        init(context);
    }

    public GoodsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GoodsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;

    private void init(Context context) {
        this.context = context;
        //创建垂直的LinearLayout
        view = View.inflate(context, R.layout.goods_lyout_v, null);
        mLyoutV = (LinearLayout) view.findViewById(R.id.lyout_v);
        //创建水平的
        addView(view);
    }

    //传递数据
    public void setList(List<HootBean.DatasBean> list) {
        mLyoutV.removeAllViews();
        //创建水平layout
        LinearLayout view_h = (LinearLayout) View.inflate(context, R.layout.goods_lyout_h, null);
        mLyoutV.addView(view_h);
        int len = 0;
        view_h.removeAllViews();
        for (int a = 0; a < list.size(); a++) {
            String msg = list.get(a).getName();
            len += msg.length();
            if (len > 18) {
                view_h = (LinearLayout) View.inflate(context, R.layout.goods_lyout_h, null);
                mLyoutV.addView(view_h);
                len = 0;
            }
            //创建展示内容的layout
            View viewContent = View.inflate(context, R.layout.goods_lyout_content, null);
            TextView textView = (TextView) viewContent.findViewById(R.id.tv_content);
            textView.setText(msg);
            view_h.addView(viewContent);

            //设置parmars

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewContent.getLayoutParams();
            params.weight = 1;
            params.leftMargin = 10;
            params.topMargin = 5;
            viewContent.setLayoutParams(params);
        }

    }
}
