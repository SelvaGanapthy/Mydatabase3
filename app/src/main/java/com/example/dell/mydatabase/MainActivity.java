package com.example.dell.mydatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Myhelper myhelper;
    EditText user, pass;
    Info info = new Info();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myhelper = new Myhelper(this);
        user = (EditText) findViewById(R.id.e1);
        pass = (EditText) findViewById(R.id.e2);
    }

    public void fetch() {
        SQLiteDatabase database = myhelper.getReadableDatabase();
        String fetch1[] = {"id", "name", "pass"};
        Cursor c = database.query("selva1", fetch1, null, null, null, null, null);
        c.moveToLast();
        if (c != null) {
            do {
                StringBuilder obj = new StringBuilder();
                obj.append("\t id = "+c.getString(0)+"\t Name = "+c.getString(1)+"\t Password = "+c.getString(2));
                Toast.makeText(getApplicationContext(),obj,Toast.LENGTH_LONG).show();

            } while (c.moveToNext());
      }
    }

    public void ss(View v) {
        String a = user.getText().toString();
        String b = pass.getText().toString();
        if (v.getId() == R.id.b1) {
            if (a.length() == 0 || b.length() == 0) {
                Snackbar.make(v, "\t \t \t\t\t\t\t Username&password Empty", Snackbar.LENGTH_SHORT).show();
            } else {

                try {
                    info.setUsername(a);
                    info.setPassword(b);
                    myhelper.insertUser(info);
                    Snackbar.make(v, " No of rows " + myhelper.insertUser(info), Snackbar.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            try {
                fetch();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
