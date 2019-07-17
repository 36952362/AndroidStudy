package com.jupiter.androidstudy.volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.LruCache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jupiter.androidstudy.R;

import org.json.JSONObject;

/**
 * 需要访问网络时，要在AndroidManifest.xml中加上访问权限
 * <uses-permission android:name="android.permission.INTERNET" />
 *
 * 同时在AndroidManifest.xml的appliction 节点下加入
 * <uses-library android:name="org.apache.http.legacy" android:required="false"/>
 */


public class VolleyActivity extends AppCompatActivity {

    private Button btnGetJson;
    private Button btnGetPic;
    private  static final String TAG = VolleyActivity.class.getName();
    private TextView txtJsonData;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Volley.newRequestQueue(this);

        init();

    }

    private void init() {
        btnGetJson = findViewById(R.id.btsGetJson);
        btnGetPic = findViewById(R.id.btnGetPic);
        txtJsonData = findViewById(R.id.txtJsonData);
        imageView = findViewById(R.id.imgVolleyPic);
    }

    public void onVolleyClick(View view) {
        if(view == btnGetJson){
            getJsonDataFromNet(this);
            return;
        }

        if(view == btnGetPic){
            getPicFromNet(this);
            return;
        }
    }

    /**
     * Volley的数据请求步骤:
     * 1. 通过Volley获取到请求队列
     * 2. 创建Json对象请求实例
     * 3. 把请求实例添加到请求队列中
     */
    private void getJsonDataFromNet(final Context context) {

        //Json数据Url
        String url = "https://bbs.sunofbeaches.com/test_data/json.json";

        //获取网络请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(context);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,  null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d(TAG, jsonObject + "");
                        txtJsonData.setText(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {

                    /**
                     *请求数据失败com.android.volley.NoConnectionError: java.net.SocketException: socket failed: EPERM (Operation not permitted)
                     * 尝试换一个低版本的模拟器
                     */
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, "请求数据失败" + volleyError);

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private void getPicFromNet(Context context) {
        /**
         * BasicNetwork.performRequest: Unexpected response code 301 for http://bbs.sunofbeaches.com/template/dreambred_c_apple/images//logo.png
         * 如果出现301错误，说明请求的资源有重定向,尝试改成https
         */

        //图片Url
        String url = "https://bbs.sunofbeaches.com/template/dreambred_c_apple/images//logo.png";

        //获取网络请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //缓存对象
        final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(30);// 最大缓存数量

        //图片缓存
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {

            //从缓存中获取图片
            @Override
            public Bitmap getBitmap(String s) {
                return cache.get(s);
            }

            //把图片存储到缓存中
            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                cache.put(s, bitmap);

            }
        };

        //把缓存对象加到请求队列中
        ImageLoader loader = new ImageLoader(requestQueue, imageCache);

        //监听
        /**
         * mPicContainer：图片容器，也就是view，这里是ImageView,
         * R.mipmap.ic_launcher : 默认图片,
         * R.mipmap.ic_launche ：加载错误的图片。
         */
        ImageLoader.ImageListener listener = loader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);

        //获取图片
        loader.get(url, listener);

    }
}
