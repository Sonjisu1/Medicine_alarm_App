package com.example.medicine_alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        ArrayList<String > list = new ArrayList<>();

        list.add("공지사항입니다.");
        list.add("공지사항2");
        list.add("공지사항3333");

        RecyclerView recyclerView  = findViewById(R.id.noticerecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoticeAdapter adapter = new NoticeAdapter(list);
        recyclerView.setAdapter(adapter);

    }
}
