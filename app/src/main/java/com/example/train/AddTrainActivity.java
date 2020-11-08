package com.example.train;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddTrainActivity extends AppCompatActivity {
    private EditText edTrainNo, edDepartureDate, edArrivalDate, edClassA, edClassB, edFrom, edTo;
    private String stTrainNo, stDepartureDate, stArrivalDate, stClassA, stClassB, stFrom, stTo;
    private Button addBtn;
    private FirebaseFirestore mDb;
    private  SharedPreferences sharedPreferences;

    private  SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train);
        init();
        deploy();
    }

    private void init() {
        setTitle("Add Train");
        mDb = FirebaseFirestore.getInstance();
        edTrainNo = findViewById(R.id.Add_Train_num);
        edDepartureDate = findViewById(R.id.Add_Train_depart);
        edArrivalDate = findViewById(R.id.Add_Train_arrival);
        edClassA = findViewById(R.id.Add_Train_classA);
        edClassB = findViewById(R.id.Add_Train_classB);
        edFrom = findViewById(R.id.Add_Train_from);
        edTo = findViewById(R.id.Add_Train_to);
        addBtn = findViewById(R.id.Add_Train_addBtn);
        sharedPreferences = getSharedPreferences("aTrain", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    private void deploy() {

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }
        });
    }

    private void Validation() {


        stTrainNo = edTrainNo.getText().toString();
        stDepartureDate = edDepartureDate.getText().toString();
        stArrivalDate = edArrivalDate.getText().toString();
        stClassA = edClassA.getText().toString();
        stClassB = edClassB.getText().toString();
        stFrom = edFrom.getText().toString();
        stTo = edTo.getText().toString();


        if (stTrainNo == null || stTrainNo.isEmpty()
                || stDepartureDate == null || stDepartureDate.isEmpty()
                || stArrivalDate == null || stArrivalDate.isEmpty()
                || stClassA == null || stClassA.isEmpty()
                || stClassB == null || stClassB.isEmpty()
                || stTo == null || stTo.isEmpty()
                || stFrom == null || stFrom.isEmpty()

        ) {
            startActivity(new Intent(this,TrainsActivity.class));
/*
            Toast.makeText(this, "Some values are missing", Toast.LENGTH_SHORT).show();
*/
        } else {
            addData(new TrainModel("id", stTrainNo, stDepartureDate, stArrivalDate, stClassA, stClassB, stFrom, stTo));
        }


    }


    private void addData(TrainModel model) {
        List<TrainModel> addList = getList();
        addList.add(model);
        setList("myList",addList);
        startActivity(new Intent(this,TrainsActivity.class));

    }

    public <T> void setList(String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }
    public void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
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