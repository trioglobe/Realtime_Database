package com.trioglobe.developers_kit_demo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;

import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<home_dbhelper> db_helper;
    DatabaseReference reference;
    home_dbhelper h_db;
    home_adapter adapter;
    SearchView search;

    RelativeLayout relative;


    public Home() {
        reference = FirebaseDatabase.getInstance().getReference("home");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recycler_home);
        relative = findViewById(R.id.relative);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    db_helper = new ArrayList<>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        h_db = dataSnapshot1.getValue(home_dbhelper.class);
                        db_helper.add(h_db);
                    }
                    adapter = new home_adapter(Home.this, db_helper, recyclerView);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(Home.this, "DATA SNAPSHOT NOT EXIST",Toast.LENGTH_LONG).show();
                }
            }
        
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        db_helper = new ArrayList<>();
      /*  search.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(final String query) {
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()) {
                                    db_helper.clear();
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        h_db = dataSnapshot1.getValue(home_dbhelper.class);
                                        if(h_db.getTitle().toLowerCase().contains(query.toLowerCase())){
                                            db_helper.add(h_db);
                                        }
                                    }
                                    if(db_helper.isEmpty()){
                                        Toast.makeText(Home.this, "Not Found", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter = new home_adapter(Home.this, db_helper, recyclerView);
                                    recyclerView.setAdapter(adapter);
                                }
                                else{
                                    Toast.makeText(Home.this, "DATA SNAPSHOT NOT EXIST",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                }
        );*/

    }

}