package supblois.alexc.ovh.supblois.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by ben on 10/12/17.
 */

public class MyDbManager implements IDbManager {

    private SQLiteDatabase database;
    private MyDB dbhelper;

    private IAccountDAO accountDAO;
    private IMessageDAO messageDAO;
    private IConnectedDAO connectedDAO;

    private static MyDbManager dbManager = null;

    private MyDbManager(Context context){
        dbhelper = new MyDB(context);
    }

    public static MyDbManager getInstance(Context context){
        if(dbManager == null){
           dbManager = new MyDbManager(context);
        }
        return dbManager;
    }

    @Override
    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
        accountDAO = new MyAccountDAO(database);
    }

    @Override
    public void close() {
        dbhelper.close();
    }

    @Override
    public IAccountDAO getAccountDAO() {
        return new MyAccountDAO(database);
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
