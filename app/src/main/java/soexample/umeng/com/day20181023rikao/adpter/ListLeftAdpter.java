package soexample.umeng.com.day20181023rikao.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.model.LeftBean;

public class ListLeftAdpter extends BaseAdapter {
    private Context context;
    private List<LeftBean.DataBean> list;

    public ListLeftAdpter(Context context, List<LeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyView myView;
        if (convertView == null) {
            myView = new MyView();
            convertView = View.inflate(context, R.layout.layout_left_item, null);
            myView.textView = convertView.findViewById(R.id.item_left_text);
            convertView.setTag(myView);
        } else {
            myView = (MyView) convertView.getTag();
        }
        myView.textView.setText(list.get(position).getName());
        return convertView;
    }

    class MyView {
        TextView textView;
    }
}
