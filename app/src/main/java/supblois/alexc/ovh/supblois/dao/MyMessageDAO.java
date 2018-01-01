package supblois.alexc.ovh.supblois.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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

    /**
     * @param sender
     * @return null or Message
     */
    @Override
    public Message getLastMsgFrom(String sender) {
        return null;
    }


    private static Message convertToMsg(Cursor cur){
        long msgId = cur.getInt(1);
        String sender = cur.getString(2);
        Date date = Timestamp.valueOf(cur.getString(3));
        String content = cur.getString(4);
        return new Message(msgId, sender, date, content);
    }

    @Override
    public ArrayList<Message> getAllLastMsg() {
        ArrayList<Message> res = new ArrayList<>();
        Cursor cur = database.query(MyDB.TABLE_MSG, MyDB.ALLCOLUMNS_MSG, "*", null, null, null, null );
        while (cur.moveToNext()){
            Message msg = convertToMsg(cur);
            res.add(msg);
        }

        return res;
    }

    @Override
    public void newMsg(Message msg) {
        newMsg(msg.getMsgId(), msg.getSenderId(), msg.getDate(), msg.getContent());
    }

    @Override
    public void newMsg(long msgid, String sender, Date date, String content) {
        ContentValues cv = new ContentValues();
        cv.put(MyDB.COLUMN_MSG_ID, msgid);
        cv.put(MyDB.COLUMN_SENDER, sender);
        cv.put(MyDB.COLUMN_DATE, date.toString());
        cv.put(MyDB.COLUMN_CONTENT, content);
        database.insert(MyDB.TABLE_MSG, null, cv);
    }

    @Override
    public void deleteAllMsgFrom(String sender) {
        database.delete(MyDB.TABLE_MSG, MyDB.COLUMN_SENDER+"="+sender, null);
    }

    @Override
    public void keepOnlyLastMsg(String sender, long numberToKeep) {
        String filter = MyDB.COLUMN_SENDER+"="+sender;
        String query = "DELETE * from "+MyDB.TABLE_MSG+" WHERE "+filter+" ORDER BY "+MyDB.COLUMN_DATE+" DESC LIMIT "+numberToKeep;
        database.rawQuery(query, null);

    }
}
