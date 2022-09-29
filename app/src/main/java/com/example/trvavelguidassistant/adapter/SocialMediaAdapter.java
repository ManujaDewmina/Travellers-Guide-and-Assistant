package com.example.trvavelguidassistant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trvavelguidassistant.R;
import com.example.trvavelguidassistant.model.SocialMediaModel;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.socialMediaVH> {

    Context context1;
    ArrayList<SocialMediaModel> items1;

    public SocialMediaAdapter(Context context1, ArrayList<SocialMediaModel> items1) {
        this.context1 = context1;
        this.items1 = items1;
    }

    @NonNull
    @Override
    public socialMediaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context1)
                .inflate(R.layout.social_media_item,parent,false);
        return new socialMediaVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull socialMediaVH holder, int position) {

        SocialMediaModel socialMediaModel = items1.get(position);
        holder.userName.setText(socialMediaModel.getUserName());
        holder.location.setText(socialMediaModel.getLocation());
    }

    @Override
    public int getItemCount() {
        return items1.size();
    }

    public static class socialMediaVH extends RecyclerView.ViewHolder {

        TextView userName,location;

        public socialMediaVH(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            location = itemView.findViewById(R.id.location);
        }
    }
}