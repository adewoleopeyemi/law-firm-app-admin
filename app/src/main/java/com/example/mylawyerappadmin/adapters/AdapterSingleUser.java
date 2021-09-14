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
import com.example.mylawyerappadmin.activities.DetailsAndApprovalActivity;
import com.example.mylawyerappadmin.models.ModelServiceProvider;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterSingleUser extends RecyclerView.Adapter<AdapterSingleUser.holder>{
    Context context;
    ArrayList<ModelServiceProvider> allUsers;
    Boolean forAssignment;

    public AdapterSingleUser(Context context, ArrayList<ModelServiceProvider> allUsers, Boolean forAssignment) {
        this.context = context;
        this.allUsers = allUsers;
        this.forAssignment = forAssignment;
    }

    @NonNull
    @NotNull
    @Override
    public holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(context).inflate(R.layout.layout_single_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull holder holder, int position) {
        holder.position = position;
        holder.name.setText(allUsers.get(position).getFullname());
        Glide.with(context).load(allUsers.get(position).getCor_profile()).placeholder(R.color.colorPrimaryDark).into(holder.profile_pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!forAssignment){
                    Intent i = new Intent(context, DetailsAndApprovalActivity.class);
                    i.putExtra("user", allUsers.get(position));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
                else {
                    startAssignLawyer(allUsers.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    class holder extends RecyclerView.ViewHolder{
        ImageView profile_pic;
        TextView name;
        int position;
        public holder(@NonNull @NotNull View itemView) {
            super(itemView);
            profile_pic = itemView.findViewById(R.id.iv_profile);
            name = itemView.findViewById(R.id.tv_name);
        }
    }

    private void startAssignLawyer(ModelServiceProvider user) {
        Intent i = new Intent(context, DetailsAndApprovalActivity.class);
        i.putExtra("user", user);
        i.putExtra("forwhat", "Assignment");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
