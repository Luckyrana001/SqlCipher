package androidluckyguys.sqlcipher;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import net.sqlcipher.database.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    // JNI part!
    static {
        System.loadLibrary("native-lib");
    }

    DBHelper db; Context context;TextView     adminId,password
            ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        adminId = (TextView)findViewById(R.id.adminId);
        password = (TextView)findViewById(R.id.password);

      /* Enabling sql cipher */
        if(DBHelper .enableSQLCypher)
        {
            SQLiteDatabase.loadLibs(context);
        }
        db = DBHelper.getInstance(context,stringFromJNIClass());
        // db = new DBHelper(this);


        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setAdminId("Admin");
        credentialModel.setPassword("123456");

        /*insert data into db */
        db.insertAdminCredential(credentialModel);



        /*fetch saved credetials data and show in textview*/
        ArrayList<CredentialModel> allSavedCredential = db.getAllToDos();
        Log.i("db data",new Gson().toJson(allSavedCredential).toString());


        CredentialModel dbData = allSavedCredential.get(0);
        adminId.setText("Admin: "+dbData.getAdminId());
        password.setText("Password: "+dbData.getPassword());

    }




    // do not forget to close db instance
    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNIClass();
}