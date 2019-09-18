package com.example.recycleview.itemonclick;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.andy.recycleview.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<studentData> studentDataList = new ArrayList<>();
    public static final String TAG = "MainActivity";
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add=findViewById(R.id.add);
        Button remove=findViewById(R.id.remove);
        recyclerView = findViewById(R.id.recycler_view);
        studentAdapter = new StudentAdapter(studentDataList,MainActivity.this);




        studentAdapter.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();

                Toast.makeText(MainActivity.this, "viewHolder.getAdapterPosition()"+viewHolder.getAdapterPosition(), Toast.LENGTH_LONG).show();


            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(studentAdapter);
        StudentDataPrepare();
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentDataList.size()>0) {
                    studentDataList.remove(studentDataList.size() - 1);
                    studentAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, String.valueOf(studentDataList.size()), Toast.LENGTH_LONG).show();
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentDataList.size()>=0) {
                    studentData data = new studentData("xiao ming", 25);
                    studentDataList.add(studentDataList.size(), data);
                    studentAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, String.valueOf(studentDataList.size()), Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void StudentDataPrepare() {
        studentData data = new studentData("sai", 25);
        studentDataList.add(data);
        data = new studentData("sai raj", 25);
        studentDataList.add(data);
        data = new studentData("raghu", 20);
        studentDataList.add(data);
        data = new studentData("raj", 28);
        studentDataList.add(data);
        data = new studentData("amar", 15);
        studentDataList.add(data);
        data = new studentData("bapu", 19);
        studentDataList.add(data);
        data = new studentData("chandra", 52);
        studentDataList.add(data);
        data = new studentData("deraj", 30);
        studentDataList.add(data);
        data = new studentData("eshanth", 28);
        studentDataList.add(data);
        studentDataList.sort(Comparator.comparingInt(studentData::getAge).reversed());
//        Collections.sort(studentDataList, new Comparator<studentData>() {
//            @Override
//            public int compare(studentData o1, studentData o2) {
//                return o1.name.compareTo(o2.name);
//            }
//        });
    }
}
