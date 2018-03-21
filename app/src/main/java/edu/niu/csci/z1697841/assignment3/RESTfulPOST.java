package edu.niu.csci.z1697841.assignment3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tim on 3/20/2018.
 */

public class RESTfulPOST extends AsyncTask<Object, Object, String> {
    private String urlParams = new String();
    private final Context context;

    public RESTfulPOST(Context context, String firstName, String lastName) {
        this.context = context;
        StringBuilder paramsBuilder = new StringBuilder();

        try {
            paramsBuilder.append("fname=");
            paramsBuilder.append(URLEncoder.encode(firstName, "UTF-8"));
            paramsBuilder.append("&lname=");
            paramsBuilder.append(URLEncoder.encode(lastName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        urlParams = paramsBuilder.toString();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "POST is being sent", Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(Object[] objects) {
        URL url;
        HttpURLConnection connection;
        DataOutputStream outputStream;
        BufferedReader reader;
        StringBuilder responseOutput = new StringBuilder();
        StringBuilder display;
        String line;
        int responseCode;

        try {
            url = new URL("http://www.jorjabrown.us/petstore/api/owners/add");
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(urlParams);
            outputStream.flush();
            outputStream.close();

            responseCode = connection.getResponseCode();

            display = new StringBuilder("Request URL: " + url);
            display.append("\nRequest Parameters: " + urlParams);
            display.append("\nResponse Code: " + responseCode);
            display.append("\nType: POST");

            Log.d("URL", String.valueOf(connection.getURL()));
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Log.i(" === output === ", reader.toString());

            while ((line = reader.readLine()) != null) {
                responseOutput.append(line);
            }
            reader.close();

            display.append("\nResponse:\n" + responseOutput);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseOutput.toString();
    }
}
