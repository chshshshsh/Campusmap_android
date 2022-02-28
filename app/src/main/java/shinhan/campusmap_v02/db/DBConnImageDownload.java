package shinhan.campusmap_v02.db;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;

public class DBConnImageDownload extends AsyncTask<String, Integer, Bitmap> {
    Bitmap bmImg;

    @Override
    protected Bitmap doInBackground(String... urls) {
        // TODO Auto-generated method stub
                try{
                    String url = "http://172.30.1.30:8080" + urls[0];
                    Log.d("Test", url);

                    URL myFileUrl = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();

                    bmImg = BitmapFactory.decodeStream(is);

                    if(bmImg != null){
                Log.d("Test", "null 아님");
            }
            else{
                Log.d("Test", "null");
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        return bmImg;
    }
}
