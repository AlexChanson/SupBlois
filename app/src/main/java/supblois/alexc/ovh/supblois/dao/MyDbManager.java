package supblois.alexc.ovh.supblois.dao;

import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by ben on 10/12/17.
 */

public class MyDbManager implements IDbManager {

    private SQLiteDatabase database;
    private MyDB dbhelper;

    private static MyDbManager dbManager = null;

    private MyDbManager(){

    }

    public static MyDbManager getInstance(){
        if(dbManager == null){
           dbManager = new MyDbManager();
        }
        return dbManager;
    }

    @Override
    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }

    @Override
    public void close() {
        dbhelper.close();
    }

    @Override
    public IAccountDAO getAccountDAO() {
        return null;
    }

    @Override
    public IConnectedDAO getConnectedDAO() {
        return null;
    }

    @Override
    public IMessageDAO getMessageDAO() {
        return null;
    }
}
