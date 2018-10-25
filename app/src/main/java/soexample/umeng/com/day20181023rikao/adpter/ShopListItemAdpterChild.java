package soexample.umeng.com.day20181023rikao.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.day20181023rikao.R;
import soexample.umeng.com.day20181023rikao.cview.EdView;
import soexample.umeng.com.day20181023rikao.model.ShopCarBean;

public class ShopListItemAdpterChild extends RecyclerView.Adapter<ShopListItemAdpterChild.MyViewhoder> {

    private List<ShopCarBean.DataBean.ListBean> list;
    private Context context;
    private int i;

    public ShopListItemAdpterChild(Context context, List<ShopCarBean.DataBean.ListBean> list, int i) {
        this.list = list;
        this.context = context;
        this.i = i;
    }


    @NonNull
    @Override
    public ShopListItemAdpterChild.MyViewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shop_layout_item_child, null);
        MyViewhoder myViewhoder = new MyViewhoder(inflate);
        return myViewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ShopListItemAdpterChild.MyViewhoder holder, final int position) {
        if (i == 1) {//瀑布流
            holder.mLinearLayout.setOrientation(LinearLayout.VERTICAL);
            holder.ischeck.setVisibility(View.GONE);

        } else {
            holder.mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            holder.ischeck.setVisibility(View.VISIBLE);
            if (list.get(position).isIscheck()) {
                holder.ischeck.setImageResource(R.drawable.cricle_yes);
            } else {
                holder.ischeck.setImageResource(R.drawable.cricle_no);
            }
            //点击单个check改变状态
            holder.ischeck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).isIscheck()) {
                        list.get(position).setIscheck(false);
                    } else {
                        list.get(position).setIscheck(true);
                    }
                    notifyItemChanged(position);
                    shopCallBack.callback();
                }
            });
        }
        holder.mTitle.setText(list.get(position).getTitle());
        holder.mPrice.setText(list.get(position).getPrice() + "");
        Glide.with(context).load(list.get(position).
                getImages().replace("https", "http").split("\\|")[0]).into(holder.imgBg);
        //设置自定义view里面的数据
        holder.viewById.setData(this,list,position);
        holder.viewById.result(new EdView.EdCallBack() {
            @Override
            public void calBack() {
                shopCallBack.callback();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewhoder extends RecyclerView.ViewHolder {
        TextView mTitle, mPrice;
        ImageView imgBg;
        ImageView ischeck;
        LinearLayout mLinearLayout;
        EdView viewById;
        public MyViewhoder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.item_child_title);
            mPrice = itemView.findViewById(R.id.item_child_price);
            imgBg = itemView.findViewById(R.id.item_child_img);
            ischeck = itemView.findViewById(R.id.item_no_check);
            mLinearLayout = itemView.findViewById(R.id.shop_car_layout);
            viewById = itemView.findViewById(R.id.shopcar_edview);
        }
    }
    private ShopCallBack shopCallBack;

    public void setState(ShopCallBack shopCallBack) {
        this.shopCallBack = shopCallBack;
    }
    public interface ShopCallBack{
        void callback();
    }
}
