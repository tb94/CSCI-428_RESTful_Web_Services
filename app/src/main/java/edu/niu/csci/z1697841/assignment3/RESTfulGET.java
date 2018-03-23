package edu.niu.csci.z1697841.assignment3;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Tim on 3/20/2018.
 * <p>
 * Takes no parameters
 * Makes a GET request to retrieve JSON data from the webservice
 * Parses the JSON to create Owner object
 * Returns the list of Owners
 */

public class RESTfulGET extends AsyncTask<Object, Object, ArrayList<Owner>> {
    private ArrayList<Owner> list = new ArrayList<>();

    public RESTfulGET() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Owner> doInBackground(Object[] objects) {
        URL url;
        HttpURLConnection connection;
        InputStream stream;
        BufferedReader reader;
        JSONArray jsonResult;
        String line;
        StringBuilder data = new StringBuilder();
        try {
            url = new URL("http://www.jorjabrown.us/petstore/api/owners/list");
            connection = (HttpURLConnection) url.openConnection();
            stream = new BufferedInputStream(connection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(stream));

            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            reader.close();

            jsonResult = new JSONArray(data.toString());

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject owner = jsonResult.getJSONObject(i);
                String oid = owner.getString("oid");
                String fname = owner.getString("fname");
                String lname = owner.getString("lname");

                if (!oid.isEmpty() && !fname.isEmpty() && !lname.isEmpty()) {
                    list.add(new Owner(oid, fname, lname));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
