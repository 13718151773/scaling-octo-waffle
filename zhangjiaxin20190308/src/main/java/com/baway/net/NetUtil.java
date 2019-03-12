package com.baway.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author：${张嘉鑫}
 * @Date：2019/3/8 9:44
 */
public class NetUtil {
    //判断网络
    public static boolean isConnection(Context context){
        if (context!=null){
            ConnectivityManager manager =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null){
                return info.isConnected();
            }
        }
        return false;
    }

    //请求数据
    public static String isDatas(String dataUrl){
        try {
            URL url = new URL(dataUrl);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if (code==200){
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                String s="";
                while ((s=reader.readLine())!=null){
                    buffer.append(s);
                }
                return buffer.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
//接口
    public interface MyCallBack{
        void getData(String dataurls);
    }
//异步加载
    public static void MyAsyncTask (final String strUrl, final MyCallBack callBack){
        new AsyncTask<String,Void,String>(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                callBack.getData(s);
            }

            @Override
            protected String doInBackground(String... strings) {
                String datas = NetUtil.isDatas(strings[0]);
                return datas;
            }
        }.execute(strUrl);
    }
}
