package shinhan.campusmap_v02.db;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBConnImage extends AsyncTask<String, Void, String> {
    private String serverURL = "http://172.30.1.30:8080/shinhan_campusmap/image/recvImage.jsp";  //<<서버주소

    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";
    String recvmsg = "";

    @Override
    protected String doInBackground(String... strings) {
        try {
            String fileName = strings[0];
            String bcode = strings[1];
            Log.d("Test", bcode);
            FileInputStream mFileInputStream = new FileInputStream(fileName);
            URL connectUrl = new URL(serverURL);
            Log.d("Test", "mFileInputStream  is " + mFileInputStream);

            // HttpURLConnection 통신
            HttpURLConnection conn = (HttpURLConnection) connectUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            // write data
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"" + bcode + "\";filename=\"" + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);

            int bytesAvailable = mFileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);

            byte[] buffer = new byte[bufferSize];
            int bytesRead = mFileInputStream.read(buffer, 0, bufferSize);

            Log.d("Test", "image byte is " + bytesRead);

            // read image
            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = mFileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = mFileInputStream.read(buffer, 0, bufferSize);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // close streams
            Log.e("Test", "File is written");
            mFileInputStream.close();
            dos.flush();
            // finish upload...

            // get response
            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuffer bf = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                bf.append(str);
            }
            recvmsg = bf.toString();

            tmp.close();
            reader.close();

            Log.d("Test", "recvmsg" + recvmsg);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Test", "exception");
            // TODO: handle exception
        }

        return recvmsg;
    }
}