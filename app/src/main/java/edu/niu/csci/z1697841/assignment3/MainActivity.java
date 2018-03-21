package edu.niu.csci.z1697841.assignment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ArrayList<Owner> results;
    TextView resultsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultsTV = findViewById(R.id.responseTextView);
    }

    public void sendGETRequest(View view) {
        StringBuilder builder = new StringBuilder();
        RESTfulGET get =  new RESTfulGET(this);

        get.execute();

        try {
            results = (ArrayList<Owner>) get.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < results.size(); i++) {
            builder.append("\n\nID: " + results.get(i).getOid());
            builder.append("\nName: " + results.get(i).getFirstName() + " " + results.get(i).getLastName());
        }

        resultsTV.setText(builder);

    }

    public void sendPOSTRequest(View view) {
        StringBuilder builder = new StringBuilder();
        RESTfulPOST post =  new RESTfulPOST(this, "Tim", "Bretz");

        post.execute();

        try {
            resultsTV.setText(post.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e){
            resultsTV.setText("Could not send POST: " + e);
        }
    }
}