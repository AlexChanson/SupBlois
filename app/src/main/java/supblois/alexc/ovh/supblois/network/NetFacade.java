package supblois.alexc.ovh.supblois.network;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
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
        Command cmd = new Command("PULL", new Object[]{pullAll}, Message[].class);
        try {
            return Arrays.asList((Message[]) Utility.getExpectedOrNull(cmd, 5));
        }catch (ClassCastException e){
            System.err.println("Error retrieving messages !");
            return null;
        }
    }

    public static List<Message> pullMessages(boolean pullAll, String filter){
        Command cmd = new Command("PULL", new Object[]{pullAll, filter}, Message[].class);
        try {
            return Arrays.asList((Message[]) Utility.getExpectedOrNull(cmd, 5));
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

    public static boolean createAccount(String phone, String prenom, String nom, String pwd){
        String password = Utility.hashSHA256(pwd);
        Command cmd = new Command("CREATE", new Object[]{phone, prenom, nom, password}, boolean.class);
        try {
            return (boolean) Utility.getExpectedOrNull(cmd, 2);
        }catch (NullPointerException e){
            return false;
        }
    }

    public static Friend addFriend(String phone){
        Command cmd = new Command("FRIEND_ADD", new Object[]{phone}, Friend.class);
        return (Friend) Utility.getExpectedOrNull(cmd, 2);
    }

    public static boolean registerFbToken(Context context){
        SharedPreferences prefs = context.getSharedPreferences("fb_token_storage", 0);
        String token = prefs.getString("tok", null);

        Command cmd = new Command("FB_TOKEN", new Object[]{token}, boolean.class);

        return (boolean) Utility.getExpectedOrNull(cmd, 2);
    }
}
