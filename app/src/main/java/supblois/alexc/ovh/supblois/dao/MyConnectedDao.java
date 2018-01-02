package supblois.alexc.ovh.supblois.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by ben on 01/01/18.
 */

public class MyConnectedDao implements IConnectedDAO {

    private SQLiteDatabase database;

    public MyConnectedDao(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public String getPasswd(String num) {
        String filter = MyDB.COLUMN_LOGGED_NUM+"="+num;
        Cursor cur = database.query(
                MyDB.TABLE_CONNECTED, MyDB.ALLCOLUMNS_CONNECTED, filter,
                null, null, null, null
        );
        if (cur.moveToNext()){

            return cur.getString(2);
        }

        return null;
    }

    @Override
    public void updatePswd(String num, String passwd) {
        ContentValues cv = new ContentValues();
        cv.put(MyDB.COLUMN_LOGGED_NUM, num);
        cv.put(MyDB.COLUMN_LOGGED_PSWD, passwd);
        database.updateWithOnConflict( MyDB.TABLE_CONNECTED, cv, null , null, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public ArrayList<ConnectedAccount> getAllConnected() {
        ArrayList<ConnectedAccount> ret = new ArrayList<>();
        Cursor cur = database.query(MyDB.TABLE_CONNECTED, MyDB.ALLCOLUMNS_CONNECTED,
                null, null, null, null, null);
        while (cur.moveToNext()){
            ret.add(new ConnectedAccount(cur.getString(1), cur.getString(2)));
        }

        return ret;
    }

    @Override
    public void forget(String num) {
        database.delete(MyDB.TABLE_CONNECTED, MyDB.COLUMN_LOGGED_NUM+"="+num, null);
    }
}
