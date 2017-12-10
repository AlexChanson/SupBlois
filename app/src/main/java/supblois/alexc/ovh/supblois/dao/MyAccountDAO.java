package supblois.alexc.ovh.supblois.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by ben on 10/12/17.
 */

public class MyAccountDAO implements IAccountDAO {

    SQLiteDatabase database;

    public static final String[] allColumns = {MyDB.COLUMN_NUM, MyDB.COLUMN_FIRSTNAME, MyDB.COLUMN_LASTNAME};

    public MyAccountDAO(SQLiteDatabase database) {
        this.database = database;
        if (database == null){
            Log.e("MyAccountDAO", "database should not be null");
        }
    }

    @Override
    public RegAccount getByNumber(String number) {
        Cursor cursor = database.query(MyDB.TABLE_ACCOUNT, allColumns, MyDB.COLUMN_NUM+"="+number,
                null, null, null, null);
        if (cursor.getCount() >= 1){
            RegAccount ret = new RegAccount(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            cursor.close();
            return ret;
        }
        cursor.close();
        return null;
    }

    @Override
    public ArrayList<RegAccount> getByFirstName(String firstName) {
        return null;
    }

    @Override
    public ArrayList<RegAccount> getByLastName(String lastName) {
        return null;
    }

    @Override
    public ArrayList<RegAccount> getAll() {
        return null;
    }

    @Override
    public void setFirstName(String number, String newFirstName) {

    }

    @Override
    public void setLastName(String number, String newLastName) {

    }

    @Override
    public void setFirstAndLastName(String number, String newFirstName, String newLastName) {

    }

    @Override
    public boolean deleteByNumber(String number) {
        String strFilter = MyDB.COLUMN_NUM + "=" + number;
        ContentValues args = new ContentValues();
        args.put(MyDB.COLUMN_NUM, number);
        if (database.delete(MyDB.TABLE_ACCOUNT, strFilter, null) < 1){
            return false;
        }
        return true;
    }
}
