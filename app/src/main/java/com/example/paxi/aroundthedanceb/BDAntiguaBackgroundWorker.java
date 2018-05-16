package com.example.paxi.aroundthedanceb;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BDAntiguaBackgroundWorker extends AsyncTask<String, Void, String>
{

    Context context;
    AlertDialog alertDialog;

    public BDAntiguaBackgroundWorker(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... voids)
    {
        String type = voids[0];
        String login_url = "http://192.168.129.2/activity_login.php";
        String insert_url2 = "http://192.168.129.2/insert_event.php";

        //region activity_login
        if(type.equals("activity_login"))
        {
            try
            {
                String email = voids[1];
                String password = voids[2];

                URL url = new URL(login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("email", "UTF-8")+"="+ URLEncoder.encode(email, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+ URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                //httpURLConnection.disconnect();

                return result;
            }

            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //endregion

        //region insertEvent
        else if(type.equals("insertevent"))
        {
            try
            {
                String name = voids[1];
                String fechaInicio = voids[2];
                String fechaFinal = voids[3];
                String description = voids[4];
                String createBy = voids[5];

                URL url = new URL(insert_url2);

                HttpURLConnection.setFollowRedirects(false);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("HEAD");
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("name", "UTF-8")          +"="+ URLEncoder.encode(name, "UTF-8")+"&"+
                        URLEncoder.encode("fechaInicio", "UTF-8")   +"="+ URLEncoder.encode(fechaInicio, "UTF-8")+"&"+
                        URLEncoder.encode("fechaFinal", "UTF-8")    +"="+ URLEncoder.encode(fechaFinal, "UTF-8")+"&"+
                        URLEncoder.encode("description", "UTF-8")   +"="+ URLEncoder.encode(description, "UTF-8")+"&"+
                        URLEncoder.encode("createBy", "UTF-8")      +"="+ URLEncoder.encode(createBy, "UTF-8");


                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            }

            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        //endregion

        return null;
    }

    @Override
    protected void onPreExecute()
    {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("LOGIN STATUS");
    }

    @Override
    protected void onPostExecute(String result)
    {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
