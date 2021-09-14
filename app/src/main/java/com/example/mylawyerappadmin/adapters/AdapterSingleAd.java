package com.example.mylawyerappadmin.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.activities.AddAndRemoveAdsActivity;
import com.example.mylawyerappadmin.activities.DetailsAndApprovalActivity;
import com.example.mylawyerappadmin.models.ModelAds;
import com.example.mylawyerappadmin.models.ModelServiceProvider;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterSingleAd extends RecyclerView.Adapter<AdapterSingleAd.holder>{
    Context context;
    ArrayList<ModelAds> allAds;

    public AdapterSingleAd(Context context, ArrayList<ModelAds> allAds) {
        this.context = context;
        this.allAds = allAds;
    }

    @NonNull
    @NotNull
    @Override
    public holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(context).inflate(R.layout.layout_single_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull holder holder, int position) {
        Glide.with(context).load(allAds.get(position).getImagePath()).placeholder(context.getDrawable(R.drawable.ic_baseline_form_24))
                .into(holder.ad_image);
        holder.organization_name.setText(allAds.get(position).getName_of_organization());
        holder.organization_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRemoveAdsActivity(allAds.get(position));
            }
        });
    }

    private void startRemoveAdsActivity(ModelAds ad) {
        Intent i = new Intent(context, AddAndRemoveAdsActivity.class);
        i.putExtra("for", "viewAndRemoval");
        i.putExtra("ad", ad);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return allAds.size();
    }

    class holder extends RecyclerView.ViewHolder{
        ImageView ad_image;
        TextView organization_name;
        public holder(@NonNull @NotNull View itemView) {
            super(itemView);
            ad_image = itemView.findViewById(R.id.iv_profile);
            organization_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
