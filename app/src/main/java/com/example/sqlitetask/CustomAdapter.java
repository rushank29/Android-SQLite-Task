package com.example.sqlitetask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList user_id, user_name, user_email, user_phone, user_gender, user_profession;
    private ArrayList user_address, user_city, user_state, user_country, user_pinCode;

    CustomAdapter(Activity activity, Context context, ArrayList user_id, ArrayList user_name, ArrayList user_email,
                  ArrayList user_phone, ArrayList user_gender, ArrayList user_profession,
                  ArrayList user_address, ArrayList user_city, ArrayList user_state,
                  ArrayList user_country, ArrayList user_pinCode) {
        this.activity=activity;
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_gender = user_gender;
        this.user_profession = user_profession;
        this.user_address = user_address;
        this.user_city = user_city;
        this.user_state = user_state;
        this.user_country = user_country;
        this.user_pinCode = user_pinCode;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.id_txt.setText(String.valueOf(user_id.get(position)));
        holder.name_txt.setText(String.valueOf(user_name.get(position)));
        holder.email_txt.setText(String.valueOf(user_email.get(position)));
        holder.gender_txt.setText(String.valueOf(user_gender.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(user_id.get(position)));
                intent.putExtra("name", String.valueOf(user_name.get(position)));
                intent.putExtra("email", String.valueOf(user_email.get(position)));
                intent.putExtra("gender", String.valueOf(user_gender.get(position)));
                intent.putExtra("phone", String.valueOf(user_phone.get(position)));
                intent.putExtra("profession", String.valueOf(user_profession.get(position)));
                intent.putExtra("address", String.valueOf(user_address.get(position)));
                intent.putExtra("city", String.valueOf(user_city.get(position)));
                intent.putExtra("state", String.valueOf(user_state.get(position)));
                intent.putExtra("country", String.valueOf(user_country.get(position)));
                intent.putExtra("pinCode", String.valueOf(user_pinCode.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt, name_txt, email_txt, gender_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            email_txt = itemView.findViewById(R.id.email_txt);
            gender_txt = itemView.findViewById(R.id.gender_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
