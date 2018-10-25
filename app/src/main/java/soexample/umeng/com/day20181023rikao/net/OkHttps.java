package soexample.umeng.com.day20181023rikao.net;

import android.os.Handler;
import android.os.Message;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * okHttp
 * post And  Get
 */
public class OkHttps {

    private final int HTTP_SUCCESS = 100;
    private final int HTTP_FAILURL = 101;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HTTP_SUCCESS:
                    lisener.success((String) msg.obj);
                    break;
                case HTTP_FAILURL:
                    lisener.fail();
                    break;
            }
        }
    };
    /*
    接口回调
     */
    private HttpLisener lisener;

    public void result(HttpLisener lisener) {
        this.lisener = lisener;
    }

    public interface HttpLisener {
        void success(String data);

        void fail();
    }

    //get提交
    public OkHttps get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request requst = new Request.Builder()
                .url(url)
                .build();
        doHttp(client, requst);
        return this;
    }

    //post提交
    public OkHttps post(String url, RequestBody body) {
        OkHttpClient client = new OkHttpClient();
        Request requst = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        doHttp(client, requst);
        return this;
    }

    private void doHttp(OkHttpClient client, Request requst) {
        final Message message = Message.obtain();
        client.newCall(requst).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                message.what = HTTP_FAILURL;
                message.obj = e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                message.what = HTTP_SUCCESS;
                message.obj = response.body().string();
                handler.sendMessage(message);
            }
        });
    }
}
