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
    public static final String TABLE_CONNECTION = "Connected";

    //columns
    public static final String COLUMN_NUM = "num";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";

    public static final String COLUMN_MSG_ID = "id";
    public static final String COLUMN_SENDER = "sender";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CONTENT = "content";

    public static final String COLUMN_LOGGED_NUM = "num";
    public static final String COLUMN_LOGGED_PSWD = "password";

    //Database info
    private static final String DATABASE_NAME = "messager.db";
    private static final int DATABASE_VERSION = 1;

    //Creation table request

    public static final String NOTNULL = " NOT NULL ";

    private static final String CREATE_TABLE_ACCOUNT = "create table "
            +
            TABLE_ACCOUNT
            +
            "("
            +
            COLUMN_NUM + "Text" + NOTNULL
            +
            COLUMN_FIRSTNAME
            +
            COLUMN_LASTNAME
            +
            ");";

    private static final String CREATE_TABLE_MSG = "create table "
            +
            TABLE_MSG
            +
            "("
            +
            COLUMN_MSG_ID
            +
            COLUMN_SENDER
            +
            COLUMN_DATE
            +
            COLUMN_CONTENT
            +
            ");";

    private static final String DATABASE_CREATE = CREATE_TABLE_ACCOUNT + "\n" +
            CREATE_TABLE_MSG;

    //Init of sqlhelper
    public MyDB(Context context) {
        super(context
                ,
                DATABASE_NAME
                ,
                null
                ,
                DATABASE_VERSION
        );
    }

    @Override
    public void
    onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void
    onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "
                +
                TABLE_ACCOUNT);
        onCreate(db);
    }
}

