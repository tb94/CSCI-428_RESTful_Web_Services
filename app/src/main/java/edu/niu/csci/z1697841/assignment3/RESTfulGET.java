package edu.niu.csci.z1697841.assignment3;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Tim on 3/20/2018.
 */

public class RESTfulGET extends AsyncTask<Object, Object, ArrayList<Owner>> {
    private ArrayList<Owner> list = new ArrayList<>();
    private Context context;

    public RESTfulGET(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "GET is being sent", Toast.LENGTH_LONG).show();
    }

    @Override
    protected ArrayList<Owner> doInBackground(Object[] objects) {
        URL url;
        HttpURLConnection connection;
        InputStream stream;
        BufferedReader reader;
        JSONArray jsonResult;
        String line;
        String data = new String();
        try {
            url = new URL("http://www.jorjabrown.us/petstore/api/owners/list");
            connection = (HttpURLConnection) url.openConnection();
            stream = new BufferedInputStream(connection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(stream));

            while ((line = reader.readLine()) != null) {
                data += line;
            }
            reader.close();

            jsonResult = new JSONArray(data);

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject owner = jsonResult.getJSONObject(i);
                String oid = owner.getString("oid");
                String fname = owner.getString("fname");
                String lname = owner.getString("lname");

                list.add(new Owner(oid, fname,lname));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
