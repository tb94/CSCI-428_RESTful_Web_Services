package edu.niu.csci.z1697841.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startOwners(View view) {
        Intent intent = new Intent(this, OwnersActivity.class);
        startActivity(intent);
    }

    public void sendPOSTRequest(View view) {
//        StringBuilder builder = new StringBuilder();
//        RESTfulPOST post =  new RESTfulPOST("Tim", "Bretz");
//
//        post.execute();
//
//        try {
//            resultsTV.setText(post.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (Exception e){
//            resultsTV.setText("Could not send POST: " + e);
//        }
    }
}