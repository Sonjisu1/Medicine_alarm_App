package com.example.medicine_alarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddMedicine extends AppCompatActivity {
    EditText edt1;
    String data;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Button btnadd =(Button)findViewById(R.id.btnadd);
        edt1 = (EditText)findViewById(R.id.edt1);

      //  Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();

       Bundle extras = getIntent().getExtras();  //MainActivity에서 보낸 이전 약 정보를 받음
        if(extras != null) {

           data = extras.getString("name1");
        edt1.setText(data);  //이전 데이터를 저장
        }


            btnadd.setOnClickListener(new View.OnClickListener() {  //저장 버튼 클릭 시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMedicine.this,MainActivity.class);
                intent.putExtra("name1",edt1.getText().toString());  ///MainActivity에 데이터 전달
                startActivity(intent);
            }
        });



    }





}
