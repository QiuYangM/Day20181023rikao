package soexample.umeng.com.day20181023rikao.mvp.view;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.RequestBody;

public interface AppCreate {

    //初始化数据
    void initiData();

    //初始化控件

    View rootView();

    //初始化布局

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    //获取上下文
    void initContext(Context context);

    //get请求
    void getString(String url,int type);
    //post请求
    void posString(String url, int type, RequestBody body);
}
