package supblois.alexc.ovh.supblois.network;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connexion {
    private static String serverAddress = "msgapp.alexc.ovh";
    private static Connexion current = null;

    private Socket socket;
    private boolean state = false;
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
        state = true;
        return true;
    }

    public Object sendCommand(Command c){
        if (!c.type.equals("LOGIN")){
            Object[] temp = new Object[c.parameters.length + 1];
            temp[0] = Token.token;
            System.arraycopy(c.parameters, 0, temp, 1, c.parameters.length);
            c.parameters = temp;
        }

        if (!state)
            return null;
        String cmd = new Gson().toJson(c);
        writer.println(cmd);
        writer.flush();
        String result = "ERROR";
        try {
            result = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result == null || result.equals("ERROR"))
            return "ERROR";
        return new Gson().fromJson(result, c.getExpected());
    }

    public void close(){
        writer.println("CLOSE");
        writer.flush();
    }

    public boolean isOpen(){
        return state;
    }

    public static Connexion getCurrent() {
        return current;
    }

    public static void setCurrent(Connexion current) {
        Connexion.current = current;
    }
}
