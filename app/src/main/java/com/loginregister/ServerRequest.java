package com.loginregister;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class ServerRequest
{
    ProgressDialog progressDialog;
    public ServerRequest(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait....");
        System.out.println("--------------------------");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback)
    {
        progressDialog.show();
        new StoreUserDataAsyncTack(user,userCallback).execute();
    }
    public class StoreUserDataAsyncTack extends AsyncTask<Void,Void,Void>
    {
        User user;
        GetUserCallback userCallback;
        public  StoreUserDataAsyncTack(User user,GetUserCallback userCallback)
        {
            this.user=user;
            this.userCallback=userCallback;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            ArrayList<NameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("name",user.name));
            param.add(new BasicNameValuePair("age",user.age+""));
            param.add(new BasicNameValuePair("username",user.username));
            param.add(new BasicNameValuePair("password", user.password));
            try {
                URL url = new URL("http://192.168.0.102/register.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(param));
                writer.flush();
                writer.close();
                os.close();
                urlConnection.connect();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String result = convertInputStreamToString(in);
                System.out.println("--------------------------"+result);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {   progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }



    public void fetchUserDataInBackground(User user,GetUserCallback userCallback)
    {
        progressDialog.show();
        new FetchUserDataAsyncTack(user,userCallback).execute();
    }
    public class FetchUserDataAsyncTack extends AsyncTask<Void,Void,User>
    {
        User user;
        GetUserCallback userCallback;
        public  FetchUserDataAsyncTack(User user,GetUserCallback userCallback)
        {
            this.user=user;
            this.userCallback=userCallback;
        }

        @Override
        protected User doInBackground(Void... params)
        {
            ArrayList<NameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("username",user.username));
            param.add(new BasicNameValuePair("password", user.password));
            try {
                URL url = new URL("http://192.168.0.102/login.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(param));
                writer.flush();
                writer.close();
                os.close();
                urlConnection.connect();
                InputStream in       = new BufferedInputStream(urlConnection.getInputStream());
                String result        = convertInputStreamToString(in);
                System.out.println(result);
                JSONObject jsonObject=new JSONObject(new JSONTokener(result));
                String name          =jsonObject.getString("name");
                int age              =jsonObject.getInt("age");
                String username      =jsonObject.getString("username");
                String password      =jsonObject.getString("password");
                user=new User(name,age,username,password);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            return user;
        }
        @Override
        protected void onPostExecute(User user)
        {   progressDialog.dismiss();
            userCallback.done(user);
            super.onPostExecute(user);
        }
    }














    private static String convertInputStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }
    private static String getQuery(ArrayList<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

