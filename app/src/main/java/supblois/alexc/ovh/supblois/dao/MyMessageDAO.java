package supblois.alexc.ovh.supblois.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateUtils;

import java.sql.Timestamp;
import java.time.Instant;
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
    public ArrayList<Message> getMsgFrom(String sender) {
        String filter = MyDB.COLUMN_SENDER+"=?";
        ArrayList<Message> res = new ArrayList<>();
        Cursor cur = database.query(MyDB.TABLE_MSG, MyDB.ALLCOLUMNS_MSG, filter, new String[] {sender}, null, null, null );
        while (cur.moveToNext()){
            Message msg = convertToMsg(cur);
            res.add(msg);
        }
        cur.close();
        return res;

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
        long msgId = cur.getInt(0);
        String sender = cur.getString(1);
        Date date = new Date(cur.getLong(2));
        String content = cur.getString(3);
        boolean sent = cur.getInt(4) != 0;
        return new Message(msgId, sender, date, sent, content);
    }

    @Override
    public ArrayList<Message> getAll() {
        ArrayList<Message> res = new ArrayList<>();
        Cursor cur = database.query(MyDB.TABLE_MSG, MyDB.ALLCOLUMNS_MSG, null, null, null, null, null );
        while (cur.moveToNext()){
            Message msg = convertToMsg(cur);
            res.add(msg);
        }
        cur.close();
        return res;
    }

    @Override
    public void newMsg(Message msg) {
        newMsg(msg.getMsgId(), msg.getSenderId(), msg.getDate().getTime(), msg.getContent(), msg.isSent());
    }

    @Override
    public void newMsg(long msgid, String sender, long date, String content, boolean sent) {
        ContentValues cv = new ContentValues();

        cv.put(MyDB.COLUMN_SENDER, sender);
        cv.put(MyDB.COLUMN_DATE, date);
        cv.put(MyDB.COLUMN_CONTENT, content);
        cv.put(MyDB.COLUMN_SENT, sent ? 1 : 0);
        database.insert(MyDB.TABLE_MSG, null, cv);
    }

    @Override
    public void deleteAllMsgFrom(String sender) {
        database.delete(MyDB.TABLE_MSG, MyDB.COLUMN_SENDER+"=?", new String[] {sender});
    }

    @Override
    public void keepOnlyLastMsg(String sender, long numberToKeep) {
        String filter = MyDB.COLUMN_SENDER+"='"+sender+"'";
        String query = "DELETE * from "+MyDB.TABLE_MSG+" WHERE "+filter+" ORDER BY "+MyDB.COLUMN_DATE+" DESC LIMIT "+numberToKeep;
        database.rawQuery(query, null);

    }

    @Override
    public void deleteAll(){
        database.delete(MyDB.TABLE_MSG, null, null);
    }
}
