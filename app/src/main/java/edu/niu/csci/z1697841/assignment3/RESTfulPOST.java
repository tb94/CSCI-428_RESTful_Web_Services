package edu.niu.csci.z1697841.assignment3;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Tim on 3/20/2018.
 *
 * Takes the context of the calling thread, and two strings
 *      for the owners first name and last name to be added.
 * Builds the parameters required to make a POST request
 * Sends the request to add JSON data to the webservice
 * Returns the response code from the webservice
 */

public class RESTfulPOST extends AsyncTask<Object, Object, String> {
    private String urlParams;

    public RESTfulPOST(String firstName, String lastName) {
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

            display = new StringBuilder("Request URL: " + url)
                    .append("\nRequest Parameters: ").append(urlParams)
                    .append("\nResponse Code: ").append(responseCode)
                    .append("\nType: POST");

            Log.d("URL", String.valueOf(connection.getURL()));
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Log.i(" === output === ", reader.toString());

            while ((line = reader.readLine()) != null) {
                responseOutput.append(line);
            }
            reader.close();

            display.append("\nResponse:\n").append(responseOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseOutput.toString();
    }
}
