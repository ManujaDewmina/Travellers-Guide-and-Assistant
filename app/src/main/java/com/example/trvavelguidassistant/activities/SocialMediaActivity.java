package com.example.trvavelguidassistant.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trvavelguidassistant.R;
import com.example.trvavelguidassistant.adapter.LogAdapter;
import com.example.trvavelguidassistant.adapter.SocialMediaAdapter;
import com.example.trvavelguidassistant.model.LogModel;
import com.example.trvavelguidassistant.model.SocialMediaModel;
import com.example.trvavelguidassistant.utilities.Constants;
import com.example.trvavelguidassistant.utilities.PreferenceManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SocialMediaActivity extends AppCompatActivity {

    RecyclerView recyclerView3;
    private PreferenceManager preferenceManager;
    SocialMediaAdapter socialMediaAdapter;
    ArrayList<SocialMediaModel> SMList;
    ImageView homeImg,addImg;
    TextView homeText,addText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        preferenceManager = new PreferenceManager(getApplicationContext());

        //set header bar name
        TextView textTitle = findViewById(R.id.textTitle);
        textTitle.setText(String.format(
                "%s %s",
                preferenceManager.getString(Constants.KEY_FIRST_NAME),
                preferenceManager.getString(Constants.KEY_LAST_NAME)
        ));

        //go back
        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        //homeImg = findViewById(R.id.homeImg);
        addImg = findViewById(R.id.addImg);
        //homeText = findViewById(R.id.homeText);
        addText = findViewById(R.id.addText);

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocialMediaActivity.this, AddSocialMediaActivity.class);
                startActivity(intent);
            }
        });

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocialMediaActivity.this, AddSocialMediaActivity.class);
                startActivity(intent);
            }
        });

        recyclerView3 = findViewById(R.id.recyclerView3);
        DatabaseReference databaseReference5 = FirebaseDatabase.getInstance().getReference("SocialMedia");

        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        SMList = new ArrayList<>();
        socialMediaAdapter = new SocialMediaAdapter(this,SMList);
        recyclerView3.setAdapter(socialMediaAdapter);


        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SMList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SocialMediaModel socialMediaModel = dataSnapshot.getValue(SocialMediaModel.class);
                    SMList.add(socialMediaModel);
                }
                socialMediaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}