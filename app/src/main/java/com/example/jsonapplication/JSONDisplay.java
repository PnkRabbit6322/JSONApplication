package com.example.jsonapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class JSONDisplay extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsondisplay);

        ItemModel item = (ItemModel) getIntent().getSerializableExtra("item");

        ImageView photo = findViewById(R.id.detail_photo);
        TextView name = findViewById(R.id.detail_name);
        TextView username = findViewById(R.id.detail_username);
        TextView email = findViewById(R.id.detail_email);
        TextView address = findViewById(R.id.detail_address);
        TextView phone = findViewById(R.id.detail_phone);
        TextView company = findViewById(R.id.detail_company);

        Glide.with(this).load("https://lebavui.github.io" + item.getPhotoSource()).into(photo);
        name.setText(item.getName());
        username.setText(item.getUserName());
        email.setText(item.getEmail());
        address.setText("Address: " + item.getStreet() + ", " + item.getSuite() + ", "
                + item.getCity() + ", " + item.getZipcode() + "\n" +
                "Geo: " + item.getLat() + ", " + item.getLng());
        phone.setText("Phone: " + item.getPhone());
        company.setText("Company: " + item.getCompanyName() + "\n" + item.getCatchPhrase() + "\n" + item.getBs());

    }
}