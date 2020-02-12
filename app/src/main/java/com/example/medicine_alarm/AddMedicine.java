package com.example.medicine_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMedicine extends AppCompatActivity {
    EditText edt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Button btnadd =(Button)findViewById(R.id.btnadd);
        edt1 = (EditText)findViewById(R.id.edt1);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMedicine.this,MainActivity.class);
                intent.putExtra("name1",edt1.getText().toString());
                startActivity(intent);
                //intent를 이용하여 데이터를 전달


                Toast.makeText(getApplicationContext(),"저장",Toast.LENGTH_SHORT).show();
                finish();




            }
        });
    }
}
