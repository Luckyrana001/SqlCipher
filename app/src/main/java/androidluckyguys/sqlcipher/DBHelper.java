package androidluckyguys.sqlcipher;

/**
 * Created by Rana lucky on 2/9/2016.
 */
import android.content.ContentValues;
import android.content.Context;

// this is first main change you can see in import of class here

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import net.sqlcipher.database.SQLiteDatabase.CursorFactory;

// um-comment below 3 import and comment above net.sqciper classes to see db in your sqlite browser

import android.database.Cursor;
/*import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;*/
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    // public static final String CIPHER_PWD = "CipherSecretString";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteDatabase db;
    private static DBHelper instance;
    private static Context context;
    public static final String DATABASE_NAME = "Sample.db";
    public static final String ADMIN_TABLE_NAME = "admin_user";
    public static final String KEY_ADMIN_ID = "admin_id";
    public static final String KEY_ADMIN_PASSWORD= "password";


    public static final String USERS_TABLE_NAME = "admin_user";

    public static final String KEY_USER_ID = "UserId";
    public static final String KEY_USER_NAME = "UserName";
    public static final String KEY_FIRST_NAME = "FirstName";
    public static final String KEY_LAST_NAME = "LastName";
    public static final String KEY_EMAIL_ADDRESS = "EmailAddress";
    public static final String KEY_PHONE = "WorkNumber";
    public static final String KEY_FAX = "FaxNumber";
    public static final String KEY_MOBILE = "CellNumber";

    public static boolean enableSQLCypher = true;



    private DBHelper(Context context, String name,CursorFactory factory, int version) {
        super(context, name, factory, version);
        DBHelper.context = context;
    }

    public static synchronized DBHelper getInstance(Context context,String pswd) {
        if (instance == null) {
            instance = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
            db = instance.getWritableDatabase(pswd);

            //uncomment it if you want to see db file in sqlite browser
           // db = instance.getWritableDatabase();
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ADMIN_TABLE_NAME + "("+KEY_ADMIN_ID+" TEXT, "+KEY_ADMIN_PASSWORD+" TEXT)");
    }

/*

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + ADMIN_TABLE_NAME
                + "("+KEY_ADMIN_ID+" TEXT, " +KEY_ADMIN_PASSWORD+" TEXT, "
                + "LastName TEXT, "
                + "UserName TEXT, "
                + "WorkNumber TEXT, "
                + "FaxNumber TEXT, "
                + "CellNumber TEXT,"
                + "UserId TEXT)");
    }
*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        onCreate(db);
    }

    /**
     * Creating a todo
     */
    public long insertAdminCredential(CredentialModel model) {
       // SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ADMIN_ID, model.getAdminId());
        values.put(KEY_ADMIN_PASSWORD, model.getPassword());
        // values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long status = db.insert(ADMIN_TABLE_NAME, null, values);

        return status;
    }


    /*
 * Updating a tag
 */
    public int updateAdminTableName(CredentialModel credentialModel) {
       // SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ADMIN_ID, credentialModel.getAdminId());

        // updating row
        return db.update(ADMIN_TABLE_NAME, values, KEY_ADMIN_ID + " = ?",
                new String[] { String.valueOf(credentialModel.getAdminId()) });
    }



    /**
     * get single todo
     */
    public CredentialModel getAdminID(long id) {
       // SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + ADMIN_TABLE_NAME + " WHERE "
                + KEY_ADMIN_ID + " = " + id;

        Log.e("query", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        CredentialModel td = new CredentialModel();
        td.setAdminId(c.getString(c.getColumnIndex(KEY_ADMIN_ID)));
        td.setPassword((c.getString(c.getColumnIndex(KEY_ADMIN_PASSWORD))));
       // td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return td;
    }

    /**
     * getting all todos
     * */
    public ArrayList<CredentialModel> getAllToDos() {
        ArrayList<CredentialModel> todos = new ArrayList<CredentialModel>();
        String selectQuery = "SELECT  * FROM " + ADMIN_TABLE_NAME;

        Log.e("query", selectQuery);

        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                CredentialModel td = new CredentialModel();
                td.setAdminId(c.getString(c.getColumnIndex(KEY_ADMIN_ID)));
                td.setPassword((c.getString(c.getColumnIndex(KEY_ADMIN_PASSWORD))));
                // td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }


}