package soexample.umeng.com.day20181023rikao.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.RequestBody;

import soexample.umeng.com.day20181023rikao.net.OkHttps;

public abstract class AppCreateimp implements AppCreate {

    private View getRootview;

    @Override
    public void initiData() {

    }

    @Override
    public View rootView() {
        return getRootview;
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        getRootview = inflater.inflate(getLayoutId(), viewGroup, false);
    }

    @Override
    public void initContext(Context context) {

    }

    //get请求
    @Override
    public void getString(String url, final int type) {
        new OkHttps().get(url).result(new OkHttps.HttpLisener() {
            @Override
            public void success(String data) {
                successString(type, data);
            }

            @Override
            public void fail() {

            }
        });
    }


    //post请求
    @Override
    public void posString(final String url, final int type, RequestBody body) {
        new OkHttps().post(url, body).result(new OkHttps.HttpLisener() {
            @Override
            public void success(String data) {
                successString(type, data);
            }

            @Override
            public void fail() {

            }
        });
    }

    //成功之后的方法
    public void successString(int type, String data) {
    }

    ;

    public abstract int getLayoutId();

    //找到控件
    SparseArray<View> views = new SparseArray<>();

    public <T extends View> T get(int id) {

        T view = (T) views.get(id);
        if (view == null) {
            view = getRootview.findViewById(id);
            views.put(id, view);
        }
        return view;
    }

    //点击事件
    public void setOnClick(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }
}
