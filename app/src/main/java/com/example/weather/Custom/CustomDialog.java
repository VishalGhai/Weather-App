package com.example.weather.Custom;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;

public class CustomDialog extends Dialog {
    Context context;
    OnCitySelectListener listenerd;
    String[] cities;

    RecyclerView rec;


    public  CustomDialog(Context context,int LayoutResource,String[] c,OnCitySelectListener d){
        super(context);
        this.context=context;
        this.cities=c;
        this.listenerd=d;
        this.setContentView(LayoutResource);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        rec=this.findViewById(R.id.rc);

        rec.setLayoutManager(new LinearLayoutManager(context));

        rec.setAdapter(new Adapter(context,cities,listenerd));
    }
}


class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

    Context context;
    String[] cities;
    OnCitySelectListener onCitySelectListener;

    public Adapter(Context context, String[] cities,OnCitySelectListener listener) {
        this.context = context;
        this.cities = cities;
        this.onCitySelectListener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.card,viewGroup,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.citytext.setText(cities[i]);
        holder.note.setText(""+cities[i].toCharArray()[0]);
        final int replace=i;
        holder.citytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCitySelectListener.OnSelected(replace);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.length;
    }
    class Holder extends RecyclerView.ViewHolder{
        TextView note,citytext;
        public Holder(@NonNull View itemView) {
            super(itemView);

            note=itemView.findViewById(R.id.note);
            citytext=itemView.findViewById(R.id.citytext);
        }
    }
}