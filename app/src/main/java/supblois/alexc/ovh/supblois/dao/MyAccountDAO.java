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

    public MyAccountDAO(SQLiteDatabase database) {
        this.database = database;
        if (database == null){
            Log.e("MyAccountDAO", "database should not be null");
        }
    }

    @Override
    public void addAccount(String num) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.COLUMN_NUM, num);
        contentValues.put(MyDB.COLUMN_FIRSTNAME, "");
        contentValues.put(MyDB.COLUMN_LASTNAME, "");
        contentValues.put(MyDB.COLUMN_UNREAD, 0);
        database.insert(MyDB.TABLE_ACCOUNT, null, contentValues);
    }

    @Override
    public void addAccount(String num, String firstname) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.COLUMN_NUM, num);
        contentValues.put(MyDB.COLUMN_FIRSTNAME, firstname);
        contentValues.put(MyDB.COLUMN_LASTNAME, "");
        contentValues.put(MyDB.COLUMN_UNREAD, 0);
        database.insert(MyDB.TABLE_ACCOUNT, null, contentValues);
    }

    @Override
    public void addAccount(String num, String firstname, String lastname) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.COLUMN_NUM, num);
        contentValues.put(MyDB.COLUMN_FIRSTNAME, firstname);
        contentValues.put(MyDB.COLUMN_LASTNAME, lastname);
        contentValues.put(MyDB.COLUMN_UNREAD, 0);
        database.insert(MyDB.TABLE_ACCOUNT, null, contentValues);
    }

    @Override
    public RegAccount getByNumber(String number) {
        Cursor cursor = database.query(MyDB.TABLE_ACCOUNT, MyDB.ALLCOLUMNS_ACCOUNT, MyDB.COLUMN_NUM+"="+number,
                null, null, null, null);
        if (cursor.getCount() >= 1){
            cursor.moveToNext();
            RegAccount ret = new RegAccount(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            cursor.close();
            return ret;
        }
        cursor.close();
        return null;
    }

    @Override
    public ArrayList<RegAccount> getByFirstName(String firstName) {
        String filter = MyDB.COLUMN_FIRSTNAME+"=?";
        Cursor cursor = database.query(MyDB.TABLE_ACCOUNT, MyDB.ALLCOLUMNS_ACCOUNT, filter, new String[] {firstName}, null, null, null );

        ArrayList<RegAccount> ret = new ArrayList<>();

        while (cursor.moveToNext()){

            ret.add(new RegAccount(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
        }
        cursor.close();
        return ret;

    }

    @Override
    public ArrayList<RegAccount> getByLastName(String lastName) {
        String filter = MyDB.COLUMN_LASTNAME+"="+lastName;
        Cursor cursor = database.query(MyDB.TABLE_ACCOUNT, MyDB.ALLCOLUMNS_ACCOUNT, filter, null, null, null, null );

        ArrayList<RegAccount> ret = new ArrayList<>();

        while (cursor.moveToNext()){

            ret.add(new RegAccount(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
        }

        return ret;
    }

    @Override
    public ArrayList<RegAccount> getAll() {
        Cursor cursor = database.query(MyDB.TABLE_ACCOUNT, MyDB.ALLCOLUMNS_ACCOUNT, null, null, null, null, null );

        ArrayList<RegAccount> ret = new ArrayList<>();

        while (cursor.moveToNext()){
            ret.add(new RegAccount(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
        }
        cursor.close();
        return ret;
    }

    @Override
    public void setFirstName(String number, String newFirstName) {
        String filter = MyDB.COLUMN_NUM+"=?";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.COLUMN_FIRSTNAME, newFirstName);
        database.update(MyDB.TABLE_ACCOUNT, contentValues, filter, new String[] {number});
    }

    @Override
    public void setLastName(String number, String newLastName) {
        String filter = MyDB.COLUMN_NUM+"=?";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.COLUMN_FIRSTNAME, newLastName);
        database.update(MyDB.TABLE_ACCOUNT, contentValues, filter, new String[] {number});
    }

    @Override
    public void setFirstAndLastName(String number, String newFirstName, String newLastName) {
        String filter = MyDB.COLUMN_NUM+"=?";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.COLUMN_FIRSTNAME, newFirstName);
        contentValues.put(MyDB.COLUMN_LASTNAME, newLastName);
        database.update(MyDB.TABLE_ACCOUNT, contentValues, filter, new String[] {number});
    }

    @Override
    public void setUnreadMsg(String number, int unread) {
        String filter = MyDB.COLUMN_NUM+"=?";
        ContentValues cv = new ContentValues();
        cv.put(MyDB.COLUMN_UNREAD, Math.max(0, unread));
        database.update(MyDB.TABLE_ACCOUNT, cv, filter, new String[] {number});
    }

    @Override
    public boolean deleteByNumber(String number) {
        if(number == null)
            return false;
        String strFilter = MyDB.COLUMN_NUM + "=?";
        int nDeleted = database.delete(MyDB.TABLE_ACCOUNT, strFilter, new String[] {number});
        if (nDeleted < 1){
            return false;
        }
        return true;
    }
}
