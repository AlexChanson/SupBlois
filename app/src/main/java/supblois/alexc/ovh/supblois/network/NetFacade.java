package supblois.alexc.ovh.supblois.network;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


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
}
