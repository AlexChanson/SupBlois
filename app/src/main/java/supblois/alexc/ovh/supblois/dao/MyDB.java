package supblois.alexc.ovh.supblois.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ben on 09/12/17.
 */

public class MyDB extends SQLiteOpenHelper {
    //Tables
    public static final String TABLE_ACCOUNT = "RegAccount";
    public static final String TABLE_MSG = "Messages";
    public static final String TABLE_CONNECTED = "ConnectedAccount";

    //columns
    public static final String COLUMN_NUM = "num";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";

    public static final String COLUMN_MSG_ID = "id";
    public static final String COLUMN_SENDER = "sender";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CONTENT = "content";

    public static final String COLUMN_LOGGED_NUM = "logged_num";
    public static final String COLUMN_LOGGED_PSWD = "password";

    //all colums
    public static final String[] ALLCOLUMNS_MSG = {COLUMN_MSG_ID, COLUMN_SENDER, COLUMN_DATE, COLUMN_CONTENT};
    public static final String[] ALLCOLUMNS_CONNECTED = {COLUMN_LOGGED_NUM, COLUMN_LOGGED_PSWD};


    //Database info
    private static final String DATABASE_NAME = "supblois.db";
    private static final int DATABASE_VERSION = 2;

    //Creation table request

    public static final String NOTNULL = " NOT NULL ";

    private static final String CREATE_TABLE_ACCOUNT = "create table "
            +
            TABLE_ACCOUNT
            +
            "("
            +
            COLUMN_NUM + " Text" + NOTNULL + ", "
            +
            COLUMN_FIRSTNAME + " Text ,"
            +
            COLUMN_LASTNAME + " Text" + ", PRIMARY KEY("+COLUMN_NUM+") "
            +
            ");";

    private static final String CREATE_TABLE_MSG = "create table "
            +
            TABLE_MSG
            +
            "("
            +
            COLUMN_MSG_ID + " integer" + NOTNULL + ", "
            +
            COLUMN_SENDER + " Text" + NOTNULL + ", "
            +
            COLUMN_DATE + " Text" + NOTNULL + ", "
            +
            COLUMN_CONTENT + " Text" + NOTNULL + ", "
            +
            " PRIMARY KEY("+COLUMN_MSG_ID+","+COLUMN_SENDER+") "
            +
            ");";

    private static final String CREATE_TABLE_CONNECTED = "create table " + TABLE_CONNECTED +
            "("
            +
            COLUMN_LOGGED_NUM + " Text" + NOTNULL + ", "
            +
            COLUMN_LOGGED_PSWD + " Text"
            + ");";

    private static final String DATABASE_CREATE = CREATE_TABLE_ACCOUNT + "\n" +
            CREATE_TABLE_MSG + "\n" +
            CREATE_TABLE_CONNECTED;

    //Init of sqlhelper
    public MyDB(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    public void dropAll(SQLiteDatabase db){
        System.out.println("Dropping tables");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MSG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONNECTED);
    }

    @Override
    public void
    onCreate(SQLiteDatabase database) {
        dropAll(database);

        System.out.println("onCreate: creating tables...");
        database.execSQL(CREATE_TABLE_ACCOUNT);
        database.execSQL(CREATE_TABLE_CONNECTED);
        database.execSQL(CREATE_TABLE_MSG);

        System.out.println("done:\n"+DATABASE_CREATE);
    }

    @Override
    public void
    onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade: upgrading tables...");
        dropAll(db);
        onCreate(db);
    }
}

