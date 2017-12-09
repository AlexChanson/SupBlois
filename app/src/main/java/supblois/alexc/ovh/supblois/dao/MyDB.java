package supblois.alexc.ovh.supblois.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ben on 09/12/17.
 */

public class MyDB extends SQLiteOpenHelper {
        //Table
        public static final String TABLE_ACCOUNT = "RegAccount";
        public static final String TABLE_MSG = "Messages";
        public static final String TABLE_CONNECTION = "Connection";

        //column
        public static final String COLUMN_NUM = "num";
        public static final String COLUMN_FIRSTNAME = "firstName";
        public static final String COLUMN_LASTNAME = "lastName";
        //Database info
        private static final String DATABASE_NAME = "messager.db";
        private static final int DATABASE_VERSION = 1;
        //Creation table request
        private static final String DATABASE_CREATE = "create table "
                        +
                        TABLE_ACCOUNT
                        +
                        "("
                        +
                        COLUMN_NUM
                        +
                        COLUMN_FIRSTNAME
                        +
                        COLUMN_LASTNAME
                        +
                        ");";

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

