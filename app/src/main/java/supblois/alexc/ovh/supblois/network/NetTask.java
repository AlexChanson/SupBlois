package supblois.alexc.ovh.supblois.network;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class NetTask extends AsyncTask<Command, Integer, Object> {
    @Override
    protected Object doInBackground(Command... commands) {
        Connexion c;
        if (Connexion.getCurrent() == null) {
            c = new Connexion();
            Connexion.setCurrent(c);
        }else
            c = Connexion.getCurrent();
        if(!c.isOpen())
            c.open();
        if (c.isOpen()){
            return c.sendCommand(commands[0]);
        }
        return null;
    }
}
