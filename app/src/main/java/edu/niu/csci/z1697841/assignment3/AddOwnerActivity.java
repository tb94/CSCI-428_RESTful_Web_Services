package edu.niu.csci.z1697841.assignment3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class AddOwnerActivity extends AppCompatActivity {
    EditText fnameET;
    EditText lnameET;

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (activity.getCurrentFocus() != null && inputManager != null) {
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                inputManager.hideSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_owner);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fnameET = findViewById(R.id.first_name_ET);
        lnameET = findViewById(R.id.last_name_ET);
    }

    public void addOwner(View view) {
        RESTfulPOST post;
        String fname = fnameET.getText().toString();
        String lname = lnameET.getText().toString();

        post = new RESTfulPOST(fname, lname);

/*        DOESNT WORK YET
            if (!fname.matches("[^a-zA-Z]") && !fname.matches("[^a-zA-Z]")) {
            Toast.makeText(this, "Name must be letters only", Toast.LENGTH_SHORT).show();
        } else {
            post = new RESTfulPOST(fname, lname);
            Toast.makeText(this, fname + " " + lname + " has been registered", Toast.LENGTH_SHORT).show();
        }*/

//        post.execute();
        fnameET.setText("");
        fnameET.clearFocus();
        lnameET.setText("");
        lnameET.clearFocus();
        Toast.makeText(this, fname + " " + lname + " has been registered", Toast.LENGTH_LONG).show();
        hideSoftKeyboard(view);
    }

}
