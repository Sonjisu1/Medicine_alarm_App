package com.example.medicine_alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddMedicine.onClickListenr{

    private TabFragment tabFragment; //tab을 구현하기위한 fragment
     private Frag2 frag2;
    String MedicineName;
    ArrayList<ListViewItem> list;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    Bundle extras; //Intent를 통한 데이터를 받기 위한 Bundle

    //AddMedicine Fragment에서 보낸 data를 받을 interface
    //AddMedicine에서 정의한 interface 구현
    @Override
    public void onInputedData(String name) {
        MedicineName = name;

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




      /*  extras = getIntent().getExtras();  //Intent로 보낸 데이터 받기
        if(extras != null) {
             MedicineName = extras.getString("name1");                //데이터를 MedicineName에 저장
            //Toast.makeText(getApplicationContext(),"데이터 받음",Toast.LENGTH_SHORT).show();





        }*/

            bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.list:          //menu 안에 item 중 id가 list인 이미지를 클릭하면
                        setFrag1(0);

                        break;
                    case R.id.person1:       //menu 안에 item 중 id 가 person1인 이미지를 클릭하면
                        setFrag1(1);
                        break;
                }
                return true;
            }
        });
        //tabFragment = new TabFragment();

        frag2 = new Frag2();


        setFrag1(0);         //첫 화면

    }
    public  void setFrag1(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //프레그멘트 교체가 일어날때
        switch (n) {
            case 0:
                ft.replace(R.id.frame, TabFragment.neInstance(MedicineName));// TabFragment 레이아웃으로 교체
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.frame, frag2);    //frag2 레이아웃으로 교체
                ft.commit();
                break;
        }
    }


    //Fragment를 호출할때 사용할 메소드
    public void callFragment(AddMedicine addMedicine){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,addMedicine).commit();
    }



}
