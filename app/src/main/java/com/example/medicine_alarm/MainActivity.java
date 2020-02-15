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

public class MainActivity extends AppCompatActivity {

    private TabFragment tabFragment; //tab을 구현하기위한 fragment
     private Frag2 frag2;

    String MedicineName;
    ArrayList<ListViewItem> list;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    Bundle extras; //Intent를 통한 데이터를 받기 위한 Bundle
    ArrayListSend arrayListSend;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        list = new ArrayList<>();

        extras = getIntent().getExtras();  //Intent로 보낸 데이터 받기
        if(extras != null) {
             MedicineName = extras.getString("name1");                //데이터를 MedicineName에 저장
            //Toast.makeText(getApplicationContext(),"데이터 받음",Toast.LENGTH_SHORT).show();

            //ListViewItem 객체 생성
         ListViewItem item = new ListViewItem();

            item.setTitle(MedicineName);                         //데이터 지정
            item.setTitle1("2알");
            item.setDesc(R.drawable.ic_person_black_24dp);
            item.setIcon(R.drawable.ic_delete_black_24dp);

            //ArrayList에 추가
            list.add(item);

            arrayListSend = new ArrayListSend();
            arrayListSend.setListViewItem(list);
            // ArrayListSend 객체에 ArrayList 저장

        }

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
                ft.replace(R.id.frame, TabFragment.neInstance(arrayListSend));// TabFragment 레이아웃으로 교체/ 데이터 전달

                ft.commit();
                break;
            case 1:
                ft.replace(R.id.frame, frag2);    //frag2 레이아웃으로 교체
                ft.commit();
                break;
        }
    }






}
