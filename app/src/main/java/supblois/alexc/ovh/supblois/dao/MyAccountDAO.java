package supblois.alexc.ovh.supblois.dao;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

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
    public RegAccount getByNumber(String number) {
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
}
