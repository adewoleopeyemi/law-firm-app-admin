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

import com.example.mylawyerappadmin.R;
import com.example.mylawyerappadmin.activities.DelegateFormActivity;
import com.example.mylawyerappadmin.activities.DetailsAndApprovalActivity;
import com.example.mylawyerappadmin.models.ModelForm;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterSingleForm extends RecyclerView.Adapter<AdapterSingleForm.holder>{
    Context ctx;
    ArrayList<ModelForm> forms;

    public AdapterSingleForm(Context ctx, ArrayList<ModelForm> forms) {
        this.ctx = ctx;
        this.forms = forms;
    }

    @NonNull
    @NotNull
    @Override
    public holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(ctx).inflate(R.layout.layout_single_form, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull holder holder, int i) {
        holder.name.setText(""+forms.get(i).getFirst_name()+" "+forms.get(i).getSurname());
        holder.email.setText(""+forms.get(i).getEmail_address());
        if (!forms.get(i).getStatus().equals("Pending")){
            holder.status.setTextColor(ctx.getResources().getColor(R.color.colorPrimaryDark));
        }
        holder.status.setText(""+forms.get(i).getStatus());
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFormsApprovalActivity(forms.get(i));
            }
        });
    }

    private void startFormsApprovalActivity(ModelForm form) {
        Intent i = new Intent(ctx, DelegateFormActivity.class);
        i.putExtra("form", form);
        ctx.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name, email, status;
        public holder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            status = itemView.findViewById(R.id.status);
        }

    }
}
