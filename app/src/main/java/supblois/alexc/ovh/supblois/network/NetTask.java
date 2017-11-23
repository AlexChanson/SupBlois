package supblois.alexc.ovh.supblois.network;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class NetTask extends AsyncTask<Integer, Integer, String> {
    @Override
    protected String doInBackground(Integer... integers) {
        String host = "137.74.117.151";
        int port = 2345;

        try {
            Socket socket = new Socket(host, port);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedInputStream reader = new BufferedInputStream(socket.getInputStream());
            writer.write("DEMO");
            writer.flush();


            int stream;
            byte[] b = new byte[4096];
            stream = reader.read(b);
            return new String(b, 0, stream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
