package com.example.train;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrainsActivity extends AppCompatActivity {
    private static final String TAG = "TrainsActivity";
    private RecyclerView mRecyclerView;
    private AddTrainAdapter mAdapter;
    private List<TrainModel> mList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains);
        init();
    }
    //after on Create
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void init() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Trains");
        //tool bar setup

        sharedPreferences = getSharedPreferences("aTrain", Context.MODE_PRIVATE);

        //
        mList = new ArrayList<>();


        //recycler view
        mRecyclerView = findViewById(R.id.recycler_Trip);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(LayoutManager);

        // adapter
        mAdapter = new AddTrainAdapter(this, mList, new AddTrainAdapter.AdapterListener() {
            @Override
            public void details(View v, TrainModel model) {
                /*Intent intent = new Intent(this, AdminFlightDetailsActivity.class);
                intent.putExtra("mModel", model);
                startActivity(intent);*/
            }

            @Override
            public void edit(View v, TrainModel model) {
                /*Intent intent = new Intent(this, EditFlightActivity.class);
                intent.putExtra("mModel", model);
                startActivity(intent);*/
            }

            @Override
            public void delete(View v, String id) {

            }
        });
        // link the adapter with the recycler
        mRecyclerView.setAdapter(mAdapter);

        //fab
        FloatingActionButton fab = findViewById(R.id.addTrip);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*customStartActivityForResult(AdminFlightActivity.this, AddFlightActivity.class);*/
            }
        });


        //method used to fetch data
        getData();
    }

    private void getData() {
        mList.clear();
        mList.addAll(getList());

        //mList.add();
        mAdapter.notifyDataSetChanged();
    }

    List<TrainModel> getList(){
        List<TrainModel> arrayItems;
        String serializedObject = sharedPreferences.getString("myList", null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<TrainModel>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);
            return arrayItems;
        }else {
            return new ArrayList<>();
        }

    }

}
