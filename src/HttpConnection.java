/**
 * Created with IntelliJ IDEA.
 * User: simonz
 * Date: 2015.03.18.
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zoli on 2015.02.25..
 */
public class HttpConnection {
    private     String url;
    private     InputStream stream;

    private     boolean isconnect;
    private     byte[] buf;

    public InputStream getStream() {
        return stream;
    }

    public byte[] getBuf() {
        return buf;
    }

    public HttpConnection(String url){
        this.url=url;

        this.isconnect=false;
    }
    public void OpenStream (){

        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection)
                    url.openConnection();
            /*conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);*/
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream ostream = conn.getInputStream();
            this.stream=ostream;
            this.isconnect=true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void CloseStream() {
        if (isconnect) {
            try {
                this.stream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            ;
        }
    }

}
