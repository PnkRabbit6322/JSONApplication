package com.example.jsonapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new DownloadTask(this).execute("https://lebavui.github.io/jsons/users.json");

    }

    @SuppressLint("StaticFieldLeak")
    class DownloadTask extends AsyncTask<String, Void, List<ItemModel>> {

        ProgressDialog dialog;
        Context context;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            dialog.setMessage("loading...");
            dialog.show();
        }

        @Override
        protected List<ItemModel> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null)
                    builder.append(line);
                reader.close();
                String jsonString = builder.toString();

                List<ItemModel> items = new ArrayList<>();
                JSONArray jArr = new JSONArray(jsonString);

                for(int i = 0; i < jArr.length(); i++){
                    JSONObject jObj = jArr.getJSONObject(i);
                    JSONObject avatarObject = jObj.getJSONObject("avatar");
                    int id = jObj.getInt("id");
                    String name = jObj.getString("name");
                    String username = jObj.getString("username");
                    String email = jObj.getString("email");
                    String thumbnailSrc = avatarObject.getString("thumbnail");
                    ItemModel item = new ItemModel(id, name, username, email, thumbnailSrc);
                    items.add(item);
                }
                return items;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ItemModel> items) {
            dialog.dismiss();

            if (items != null) {
                Log.v("TAG","Size" + items.size());
                ItemAdapter adapter = new ItemAdapter(items);
                recyclerView.setAdapter(adapter);
            }
        }
    }
}