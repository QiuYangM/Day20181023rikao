package soexample.umeng.com.day20181023rikao.adpter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.model.ShopCarBean;

public class ShopListItemAdpter extends RecyclerView.Adapter<ShopListItemAdpter.MyViewhoder> {

    private List<ShopCarBean.DataBean> list = new ArrayList<>();
    private Context context;
    private int i = 1;

    public ShopListItemAdpter(Context context, int i) {
        this.context = context;
        this.i = i;
    }

    @NonNull
    @Override
    public ShopListItemAdpter.MyViewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shop_layout_item, null);
        MyViewhoder myViewhoder = new MyViewhoder(inflate);
        return myViewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopListItemAdpter.MyViewhoder holder, int position) {
        holder.mTextView.setText(list.get(position).getSellerName());
        final List<ShopCarBean.DataBean.ListBean> data = list.get(position).getList();

        //每个item的适配器
        ShopListItemAdpterChild child = null;
        StaggeredGridLayoutManager manager = null;
        //给他设置管理器
        if (i == 1) {
            child = new ShopListItemAdpterChild(context, data, 1);
            manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            holder.mRecyclerView.setLayoutManager(manager);
        } else {
            child = new ShopListItemAdpterChild(context, data, 0);
            LinearLayoutManager managers = new LinearLayoutManager(context);
            holder.mRecyclerView.setLayoutManager(managers);
        }
        holder.mRecyclerView.setAdapter(child);
        child.setState(new ShopListItemAdpterChild.ShopCallBack() {
            @Override
            public void callback() {
                shopCallBack.callback(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ShopCarBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewhoder extends RecyclerView.ViewHolder {
        TextView mTextView;
        RecyclerView mRecyclerView;


        public MyViewhoder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.shop_item_text);
            mRecyclerView = itemView.findViewById(R.id.shop_item_recy_view);

        }
    }

    private ShopCallBack shopCallBack;

    public void setState(ShopCallBack shopCallBack) {
        this.shopCallBack = shopCallBack;
    }

    public interface ShopCallBack {
        void callback(List<ShopCarBean.DataBean> list);
    }
}
