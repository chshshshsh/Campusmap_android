package shinhan.campusmap_v02.db;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DBConnAsync extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;


    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("http://172.30.1.30:8080/shinhan_campusmap/"+strings[0]+".jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestMethod("POST");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            Log.d("Test", conn.toString());

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            sendMsg = strings[1];

            osw.write(sendMsg);
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                String str;
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();

            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
}
