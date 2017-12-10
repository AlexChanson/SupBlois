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
            cursor.moveToNext();
            RegAccount ret = new RegAccount(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            cursor.close();
            return ret;
        }
        cursor.close();
        return null;
    }

    @Override
    public ArrayList<RegAccount> getByFirstName(String firstName) {
        String filter = MyDB.COLUMN_FIRSTNAME+"="+firstName;
        Cursor cursor = database.query(MyDB.TABLE_ACCOUNT, allColumns, filter, null, null, null, null );

        ArrayList<RegAccount> ret = new ArrayList<>();

        while (cursor.moveToNext()){

            ret.add(new RegAccount(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }

        return ret;

    }

    @Override
    public ArrayList<RegAccount> getByLastName(String lastName) {
        String filter = MyDB.COLUMN_LASTNAME+"="+lastName;
        Cursor cursor = database.query(MyDB.TABLE_ACCOUNT, allColumns, filter, null, null, null, null );

        ArrayList<RegAccount> ret = new ArrayList<>();

        while (cursor.moveToNext()){

            ret.add(new RegAccount(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }

        return ret;
    }

    @Override
    public ArrayList<RegAccount> getAll() {
        Cursor cursor = database.query(MyDB.TABLE_ACCOUNT, allColumns, null, null, null, null, null );

        ArrayList<RegAccount> ret = new ArrayList<>();

        while (cursor.moveToNext()){
            ret.add(new RegAccount(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }

        return ret;
    }

    @Override
    public void setFirstName(String number, String newFirstName) {
        String filter = MyDB.COLUMN_NUM+"="+number;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.COLUMN_FIRSTNAME, newFirstName);
        database.update(MyDB.TABLE_ACCOUNT, contentValues, filter, null);
    }

    @Override
    public void setLastName(String number, String newLastName) {
        String filter = MyDB.COLUMN_NUM+"="+number;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.COLUMN_FIRSTNAME, newLastName);
        database.update(MyDB.TABLE_ACCOUNT, contentValues, filter, null);
    }

    @Override
    public void setFirstAndLastName(String number, String newFirstName, String newLastName) {
        String filter = MyDB.COLUMN_NUM+"="+number;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.COLUMN_FIRSTNAME, newFirstName);
        contentValues.put(MyDB.COLUMN_LASTNAME, newLastName);
        database.update(MyDB.TABLE_ACCOUNT, contentValues, filter, null);
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
