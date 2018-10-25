package soexample.umeng.com.day20181023rikao.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.model.RightBean;

public class ListRightAdpter extends RecyclerView.Adapter<ListRightAdpter.MyViewhdler> {

    private Context context;
    private List<RightBean.DataBean> list;

    public ListRightAdpter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ListRightAdpter.MyViewhdler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.layout_right_item, null);
        MyViewhdler myViewhdler = new MyViewhdler(inflate);
        return myViewhdler;
    }

    @Override
    public void onBindViewHolder(@NonNull ListRightAdpter.MyViewhdler holder, int position) {
        //设置适配器
        ListRightItemAdpter adpter = new
                ListRightItemAdpter(context, list.get(position).getList());
        //设置管理器
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        holder.mRecyclerView.setLayoutManager(manager);
        holder.mRecyclerView.setAdapter(adpter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<RightBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewhdler extends RecyclerView.ViewHolder {
        RecyclerView mRecyclerView;

        public MyViewhdler(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.right_recyview);
        }
    }
}
