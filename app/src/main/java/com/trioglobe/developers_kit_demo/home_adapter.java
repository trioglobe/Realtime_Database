package com.trioglobe.developers_kit_demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class home_adapter  extends  RecyclerView.Adapter <home_adapter.MyViewHolder> {
    private Context mContext;
    private List<home_dbhelper> urlList;
    home_dbhelper home_dbhelper;
    RecyclerView recycler;
    Context context;

    public home_adapter(Context mContext, List<home_dbhelper> urlList, RecyclerView recycler) {
        this.mContext = mContext;
        this.urlList = urlList;
        this.recycler = recycler;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_home, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        home_dbhelper = urlList.get(position);
        final String id = home_dbhelper.getId();
        final String title = home_dbhelper.getTitle();
        final String image = home_dbhelper.getImage();
        holder.title.setText(title);
        holder.cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //anything you want
            }
        });
       // Picasso.get().load(home_dbhelper.getImage()).placeholder(R.drawable.white).fit().into(holder.imgview);
        Glide.with(mContext).load(home_dbhelper.getImage()).placeholder(R.drawable.gif).fitCenter().into(holder.imgview);
       // Picasso.get().load(home_dbhelper.getImage()).placeholder(R.drawable.white).fit().into(holder.imgview);
    }



    @Override
    public int getItemCount() {
        return urlList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardview1;
        ImageView imgview ;
        TextView title;



        public MyViewHolder(View itemView) {
            super(itemView);
            cardview1 = itemView.findViewById(R.id.cardview1);

            title = itemView.findViewById(R.id.tittle);
            imgview = itemView.findViewById(R.id.imgview);
        }
    }

}
