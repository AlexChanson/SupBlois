package supblois.alexc.ovh.supblois.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ben on 09/12/17.
 */

public interface IMessageDAO {
    public abstract ArrayList<Message> getAll();
    public abstract ArrayList<Message> getMsgFrom(long sender);
    public abstract Message getLastMsgFrom(long sender);
    public abstract HashMap<Long, Message> getAllLastMsg();

    public abstract void newMsg(Message msg);
    public void newMsg(long msgid, long sender, Date date, String content);

    public void deleteAllMsgFrom(long sender);
    public abstract void keepOnlyLastMsg(long sender, long numberToKeep);
}
