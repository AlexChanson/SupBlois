package supblois.alexc.ovh.supblois.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ben on 10/12/17.
 */

public class MyMessageDAO implements IMessageDAO {

    private SQLiteDatabase database;

    public MyMessageDAO(SQLiteDatabase database){
        this.database = database;
    }

    @Override
    public ArrayList<Message> getAll() {
        return null;
    }

    @Override
    public ArrayList<Message> getMsgFrom(long sender) {
        return null;
    }

    @Override
    public Message getLastMsgFrom(long sender) {
        return null;
    }

    @Override
    public HashMap<Long, Message> getAllLastMsg() {
        return null;
    }

    @Override
    public void newMsg(Message msg) {

    }

    @Override
    public void newMsg(long msgid, long sender, Date date, String content) {

    }

    @Override
    public void deleteAllMsgFrom(long sender) {

    }

    @Override
    public void keepOnlyLastMsg(long sender, long numberToKeep) {

    }
}
