package com.example.dazhuang.dz_android_project.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.utils.LocationUtil;
import com.example.dazhuang.dz_android_project.view.guideView.Guide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;

/**
 * @author fangyou
 * @time 2019/6/19
 */
public class LocationActivity extends BaseActivity {
    @BindView(R.id.tv_text)
    TextView tv_text;
    @BindView(R.id.image)
    ImageView image;
    private Bitmap img;
    private ImageHandler imgHandler = new ImageHandler();
    private String url = "http://172.17.4.67:8080/getVerifyCode";
    @Override
    protected int getLayout() {
        return R.layout.activity_location;
    }

    @Override
    protected void initEventAndData() {
        if (Build.VERSION.SDK_INT >= 23) {
            //如果用户并没有同意该权限
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            } else {
                LocationUtil.getCurrentLocation(getContext(), callBack);
            }
        }
        Glide.with(getContext())
                .load(url)

                .into(image);
        getHttpBitmap("http://172.17.4.67:8080/getVerifyCode");
//getCodeImage("http://172.17.4.67:8080/getVerifyCode");
//        try {
//            URL url = new URL("http://172.17.4.67:8080/getVerifyCode");
//            InputStream  is = url.openStream();
//            Bitmap  bitmap = BitmapFactory.decodeStream(is);
//            Drawable drawable = new BitmapDrawable(bitmap);
//            image.setImageDrawable(drawable);
//        } catch (Exception e) {
//            e.printStackTrace();
//            e.printStackTrace();
//        }
//        downloadImg();

    }

    /**
     * 异步从服务器加载图片数据
     */
    private void downloadImg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap img = getImg();
                Message msg = imgHandler.obtainMessage();
                msg.what = 0;
                msg.obj = img;
                imgHandler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 异步线程请求到的图片数据，利用Handler，在主线程中显示
     */
    class ImageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0:
                    img = (Bitmap) msg.obj;
                    if (img != null) {
                        image.setImageBitmap(img);
                    }
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 从服务器读取图片流数据，并转换为Bitmap格式
     *
     * @return Bitmap
     */
    private Bitmap getImg() {
        Bitmap img = null;

        try {
            URL imgUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();

            conn.setRequestMethod("POST");
            conn.setConnectTimeout(1000 * 6);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.connect();

            //输出流写参数
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            String param = getParam();
            dos.writeBytes(param);
            dos.flush();
            dos.close();

            int resultCode = conn.getResponseCode();

            if (HttpURLConnection.HTTP_OK == resultCode) {
                InputStream is = conn.getInputStream();
                img = BitmapFactory.decodeStream(is);
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }


    public void getCodeImage(final String path) {
        new Thread() {
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
                    conn.setConnectTimeout(30 * 1000);
                    conn.setRequestMethod("POST");
                    InputStream inputStream = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    image.setImageBitmap(bitmap);
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    private String getParam() {
        JSONObject jsObj = new JSONObject();
        try {
            jsObj.put("picFormat", "jpg");
            jsObj.put("testParam", "9527");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsObj.toString();
    }




    public void getHttpBitmap(final String urlString) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(urlString);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    Bitmap bitmap = null;
                    final InputStream is = connection.getInputStream();
                    if (is == null) {
                        throw new RuntimeException("stream is null");
                    } else {
                        try {
                            byte[] data = readStream(is);
                            if (data != null) {
                                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        is.close();
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    // 将服务器返回的结果存放到Message中
                    message.obj = bitmap;
                    handler.sendMessage(message);
                } catch (Exception e) {

                    e.printStackTrace();

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }




    /*
     * 得到图片字节流 数组大小
     * */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }




    public final static int SHOW_RESPONSE = 110;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    //然后使用方法decodeByteArray（）方法解析编码，生成Bitmap对象。
                    image.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };





    private LocationUtil.LocationCallBack callBack = new LocationUtil.LocationCallBack() {
        @Override
        public void onSuccess(Location location) {
            tv_text.append("经度: " + location.getLongitude() + " 纬度: " + location.getLatitude() + "\n");
        }

        @Override
        public void onFail(String msg) {
            tv_text.append(msg + "\n");
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationUtil.getCurrentLocation(getContext(), callBack);
            } else {
                tv_text.append("权限没获取！！！" + "\n");
            }
        }
    }
}
