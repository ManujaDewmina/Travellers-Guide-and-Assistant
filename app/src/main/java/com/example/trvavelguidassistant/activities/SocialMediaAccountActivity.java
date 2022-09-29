package com.example.trvavelguidassistant.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trvavelguidassistant.R;
import com.example.trvavelguidassistant.adapter.SocialMediaAdapter;
import com.example.trvavelguidassistant.model.SocialMediaModel;
import com.example.trvavelguidassistant.utilities.Constants;
import com.example.trvavelguidassistant.utilities.PreferenceManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SocialMediaAccountActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;
    ImageView homeImg,addImg,accountImg;
    CircleImageView userPic;
    TextView profileUserName;
    private StorageReference storageReference;
    RecyclerView recyclerView4;
    SocialMediaAdapter socialMediaAdapter1;
    ArrayList<SocialMediaModel> SMList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_account);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("Profile_Picture");

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
                Intent intent = new Intent(getApplicationContext(), SocialMediaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        //homeImg = findViewById(R.id.homeImg);
        addImg = findViewById(R.id.addImg);
        //homeText = findViewById(R.id.homeText);
        homeImg = findViewById(R.id.homeImg);

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocialMediaAccountActivity.this, AddSocialMediaActivity.class);
                startActivity(intent);
            }
        });

        homeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SocialMediaAccountActivity.this, SocialMediaActivity.class);
                startActivity(intent);
            }
        });

        profileUserName =findViewById(R.id.profileUserName);

        profileUserName.setText(textTitle.getText().toString());

        userPic = findViewById(R.id.userPic);

        StorageReference storageReference2 = storage.getReference().child("Profile_Picture/" + preferenceManager.getString(Constants.KEY_PROFILE_PICTURE));


        try {
            File localFile = File.createTempFile("tempProfilePicture","jpg");
            storageReference2.getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        userPic.setImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> Toast.makeText(SocialMediaAccountActivity.this, "User has no Profile Picture", Toast.LENGTH_SHORT).show());
        } catch (IOException e) {
            e.printStackTrace();
        }

        recyclerView4 = findViewById(R.id.recyclerView4);
        DatabaseReference databaseReference6 = FirebaseDatabase.getInstance().getReference("SocialMedia");

        recyclerView4.setHasFixedSize(true);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));

        SMList1 = new ArrayList<>();
        socialMediaAdapter1 = new SocialMediaAdapter(this,SMList1);
        recyclerView4.setAdapter(socialMediaAdapter1);


        databaseReference6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SMList1.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SocialMediaModel socialMediaModel = dataSnapshot.getValue(SocialMediaModel.class);
                    SMList1.add(socialMediaModel);
                }
                socialMediaAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), SocialMediaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}