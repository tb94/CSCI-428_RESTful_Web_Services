package edu.niu.csci.z1697841.assignment3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Tim Bretz
 *         <p>
 *         Displays app info
 */

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
