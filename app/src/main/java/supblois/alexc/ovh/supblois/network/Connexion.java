package supblois.alexc.ovh.supblois.network;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connexion {
    private static String serverAddress = "msgapp.alexc.ovh";

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader input;

    public Connexion() {

    }

    public boolean open(){
        InetAddress ipSrv = null;
        try {
            ipSrv = InetAddress.getByName(serverAddress);
        } catch (UnknownHostException e) {
            return false;
        }

        try {
            socket = new Socket(ipSrv, 2345);
        } catch (IOException e) {
            return false;
        }

        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                return false;
            }
            return false;
        }

        return true;
    }

    public Object sendCommand(Command c){
        String cmd = new Gson().toJson(c);
        writer.println(cmd);
        writer.flush();
        String result = "ERROR";
        try {
            result = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.equals("ERROR"))
            return "ERROR";
        return new Gson().fromJson(result, c.getExpected());
    }

    public void close(){
        writer.println("CLOSE");
        writer.flush();
    }
}
