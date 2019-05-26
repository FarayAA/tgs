package com.example.tgsmobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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

public class Email extends AsyncTask <String, Void, String> {
    AlertDialog dialog;
    Context context;
    public Boolean login = false;

    public Email(Context context){
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Email Status");
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.setMessage(s);
        dialog.show();
        if(s.contains("Berhasil"))
        {
            dialog.dismiss();
            Toast.makeText(context.getApplicationContext(),"Berhasil Terkirim",Toast.LENGTH_LONG).show();
        }else{
            dialog.dismiss();
            Toast.makeText(context.getApplicationContext(),"Gagal Terkirim",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected String doInBackground(String... voids) {
        String result = "";
        String NIM = voids[0];
        String nama = voids[1];
        String kelas = voids[2];
        String email = voids[3];

        String connstr = "http://nurchim.000webhostapp.com/UTS_Mobile/UTSmobilekamis.php";

        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("NIM","UTF-8")+"="+ URLEncoder.encode(NIM,"UTF-8")
                    +"&&"+URLEncoder.encode("nama","UTF-8")+"="+URLEncoder.encode(nama,"UTF-8")
                    +"&&"+URLEncoder.encode("kelas","UTF-8")+"="+URLEncoder.encode(kelas,"UTF-8")
                    +"&&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
            String line ="";
            while ((line = reader.readLine()) != null)
            {
                result += line;
            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }


        return result;
    }
}
