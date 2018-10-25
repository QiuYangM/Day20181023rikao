package soexample.umeng.com.day20181023rikao.cview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.adpter.ShopListItemAdpterChild;
import soexample.umeng.com.day20181023rikao.model.ShopCarBean;

public class EdView extends LinearLayout implements View.OnClickListener {

    public EdView(Context context) {
        super(context);
        init(context);
    }


    public EdView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EdView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private EditText editText;
    private Context context;
    private void init(Context context) {
        this.context = context;
        View inflate = View.inflate(context, R.layout.layout_cview_ed, null);
        ImageView addImg = inflate.findViewById(R.id.cview_plus);
        editText = inflate.findViewById(R.id.cview_ed);
        ImageView miusImg = inflate.findViewById(R.id.cview_minus);
        addImg.setOnClickListener(this);
        miusImg.setOnClickListener(this);
        addView(inflate);
    }

    private int num=1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cview_plus://加
                num++;
                editText.setText(num + "");
                list.get(position).setNum(num);
                edCallBack.calBack();
                shopListItemAdpterChild.notifyItemChanged(position);
                break;
            case R.id.cview_minus://减
                if (num>1) {
                    num--;
                }else {
                    Toast.makeText(context, "不能再继续了 额", Toast.LENGTH_SHORT).show();
                }
                editText.setText(num + "");
                list.get(position).setNum(num);
                edCallBack.calBack();
                shopListItemAdpterChild.notifyItemChanged(position);
                break;
        }
    }

    //传递数据
    private List<ShopCarBean.DataBean.ListBean> list = new ArrayList<>();
    private int position;
    private ShopListItemAdpterChild shopListItemAdpterChild;

    public void setData(ShopListItemAdpterChild shopListItemAdpterChild, List<ShopCarBean.DataBean.ListBean> list, int position) {
        this.list = list;
        this.position = position;
        this.shopListItemAdpterChild = shopListItemAdpterChild;
        num= list.get(position).getNum();
        editText.setText(num+"");
    }

    private EdCallBack edCallBack;
    public void result(EdCallBack edCallBack) {
        this.edCallBack = edCallBack;
    }
    public interface EdCallBack{
        void calBack();
    }
}
