package soexample.umeng.com.day20181023rikao.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.model.RightBean;

public class ListRightItemAdpter extends RecyclerView.Adapter<ListRightItemAdpter.MyViewhdler> {

    private Context context;
    private List<RightBean.DataBean.ListBean> list ;

    public ListRightItemAdpter(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListRightItemAdpter.MyViewhdler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.layout_right_child_item, null);
        MyViewhdler myViewhdler = new MyViewhdler(inflate);
        return myViewhdler;
    }

    @Override
    public void onBindViewHolder(@NonNull ListRightItemAdpter.MyViewhdler holder, int position) {
        holder.text.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getIcon()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewhdler extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text;

        public MyViewhdler(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_right_img);
            text = itemView.findViewById(R.id.item_right_text);
        }
    }
}
