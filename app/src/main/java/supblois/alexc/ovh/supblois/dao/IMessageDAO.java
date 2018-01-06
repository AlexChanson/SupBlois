package supblois.alexc.ovh.supblois.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ben on 09/12/17.
 */

public interface IMessageDAO {
    public abstract ArrayList<Message> getAll();
    public abstract ArrayList<Message> getMsgFrom(String sender);
    public abstract Message getLastMsgFrom(String sender);

    public abstract void newMsg(Message msg);
    public void newMsg(long msgid, String sender, long date, String content, boolean receisentved);

    public void deleteAllMsgFrom(String sender);
    public abstract void keepOnlyLastMsg(String sender, long numberToKeep);
}
