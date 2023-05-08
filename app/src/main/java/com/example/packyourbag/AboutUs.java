package com.example.packyourbag;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    ImageView imageGitHub,imgLinkdIn;
    TextView txtEmailId,txtWebsiteUrl;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //back button
        getSupportActionBar().setTitle("About Us");

        imageGitHub = findViewById(R.id.imgGitHub);
        imgLinkdIn= findViewById(R.id.imgLinkdIn);
        txtEmailId = findViewById(R.id.txtEmail);
        txtWebsiteUrl = findViewById(R.id.txtWebsite);

        imageGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/vasuparsaniya"));
                startActivity(intent);
            }
        });

        imgLinkdIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("linkedin.com/in/vasu-parsaniya-84a0a620b/"));
                startActivity(intent);
            }
        });

        txtEmailId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:vasuparsaniya21@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "From Pack Your Bag");
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    System.out.println(e);
                }
            }
        });



        txtWebsiteUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.google.com/"));
                startActivity(intent);
            }
        });


//        @Override
//        public boolean onSupportNavigateUp() {
//            onBackPressed();
//            return true;
//        }


    }
}