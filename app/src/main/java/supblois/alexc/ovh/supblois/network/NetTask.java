package supblois.alexc.ovh.supblois.network;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class NetTask extends AsyncTask<Command, Integer, Object> {
    @Override
    protected Object doInBackground(Command... commands) {
        Connexion c = new Connexion();
        if (c.open()){
            Object res = c.sendCommand(commands[0]);
            return res;
        }
        return "CONNEXION_ERROR";
    }
}
