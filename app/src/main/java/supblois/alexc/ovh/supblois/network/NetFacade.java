package supblois.alexc.ovh.supblois.network;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import supblois.alexc.ovh.supblois.Utility;
import supblois.alexc.ovh.supblois.dao.Message;


public class NetFacade {
    public static boolean login(String user, String hash){
        Command cmd = new Command("LOGIN", new Object[]{user, hash}, String.class);
        NetTask netTask = new NetTask();
        netTask.execute(cmd);
        Object ret = null;
        try {
            ret = netTask.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        if (ret != null){
            String[] sa = ret.toString().split("_");
            if (sa.length >= 3){
                Token.token = Long.parseLong(sa[2]);
                return true;
            }
        }
        return false;
    }

    public static List<Message> pullMessages(boolean pullAll){
        List<Message> messageList = new ArrayList<Message>();
        Command cmd = new Command("PULL", new Object[]{pullAll}, messageList.getClass());
        try {
            return  (List<Message>) Utility.getExpectedOrNull(cmd, 5);
        }catch (ClassCastException e){
            System.err.println("Error retrieving messages !");
            return null;
        }
    }

    public static List<Message> pullMessages(boolean pullAll, String filter){
        List<Message> messageList = new ArrayList<Message>();
        Command cmd = new Command("PULL", new Object[]{pullAll, filter}, messageList.getClass());
        try {
            return  (List<Message>) Utility.getExpectedOrNull(cmd, 5);
        }catch (ClassCastException e){
            System.err.println("Error retrieving messages !");
            return null;
        }
    }

    public static boolean pushMessage(String msg, String number){
        Command cmd = new Command("PUSH", new Object[]{msg, number}, boolean.class);
        try {
            return (boolean) Utility.getExpectedOrNull(cmd, 2);
        }catch (NullPointerException e){
            return false;
        }
    }
}
