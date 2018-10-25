package soexample.umeng.com.day20181023rikao.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.model.LeftBean;

public class HomeGridAdpter extends RecyclerView.Adapter<HomeGridAdpter.MyViewhodler> {
    private Context context;
    private List<LeftBean.DataBean> list;

    public HomeGridAdpter(Context context, List<LeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeGridAdpter.MyViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.home_grid_list_item, null);
        MyViewhodler myViewhodler = new MyViewhodler(inflate);
        return myViewhodler;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeGridAdpter.MyViewhodler holder, int position) {
        holder.tx.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getIcon()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewhodler extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tx;

        public MyViewhodler(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.home_item_img);
            tx = itemView.findViewById(R.id.home_item_text);
        }
    }
}
