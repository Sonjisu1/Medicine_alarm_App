package com.example.medicine_alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DirectAction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Frag1.onClickListenr{

    private TabFragment tabFragment; //tab을 구현하기위한 fragment
     private Frag2 frag2;

     String value;
    ListViewItem item;
    String data;
    String account;
    String MedicineName;
    ArrayList<ListViewItem> list;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private CustomDialog customDialog;
    Bundle extras; //Intent를 통한 데이터를 받기 위한 Bundle
    int pre_hour;
    int pre_minute;





    @Override
    public void onInputedData(String name,int pre_hour,int pre_minute) {// Frag1에서 받은 데이터를 MainActivity에서 사용하기 위한 오버라이딩

        data = name;      //받은 데이터를 data 변수에 저장
        this.pre_hour = pre_minute;
        this.pre_minute=pre_minute;



            Intent intent = new Intent(MainActivity.this, AddMedicine.class);
            intent.putExtra("name1", data);  //Addmedicine Activity에 데이터전달
            intent.putExtra("hour",pre_hour);
            intent.putExtra("minute",pre_minute);

            startActivity(intent);  //호출



    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        list = new ArrayList<>();
        extras = getIntent().getExtras();




         //splashvalue = extras.getString("dialog");

      //   Toast.makeText(getApplicationContext(),splashvalue,Toast.LENGTH_SHORT);


        Intent intent1 = getIntent();
        if(extras !=null){ //Notification 생성 후 AlarmReceiver에서 값을 얻어옴

            value=intent1.getStringExtra("medicine");  //약 이름 전달 받기

           Toast.makeText(getApplicationContext(),value+"",Toast.LENGTH_SHORT).show();
          customDialog = new CustomDialog(this,mdelayListener, mssListener,value); //리스너 등록
          customDialog.show();//Notification 클릭 후 앱 실행시 dialog 나오게 함


           /* AlertDialog.Builder builder = new AlertDialog.Builder(this); //Notification 클릭 후 앱 실행시 dialog 나오게 함
            builder.setTitle("약 드실 시간입니다."+ splashvalue);
            builder.setPositiveButton("예",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"예를 선택햇습니다.",Toast.LENGTH_SHORT).show();

                        }
                    });
            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();*/


        }







/*        extras = getIntent().getExtras();  //AddMedicine에서 보낸 데이터 받기
        if(extras != null) {
             MedicineName = extras.getString("name1");//데이터를 MedicineName에 저장
            account = extras.getString("account");
            //Toast.makeText(getApplicationContext(),"데이터 받음",Toast.LENGTH_SHORT).show();

            //ListViewItem 객체 생성
          item = new ListViewItem();

            item.setTitle(MedicineName);                         //데이터 지정
            item.setTitle1(account);

            item.setIcon(R.drawable.ic_delete_black_24dp);



        }
*/
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

    private View.OnClickListener mdelayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            customDialog.dismiss();
        }
    };

    private View.OnClickListener mssListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            customDialog.dismiss();
        }
    };

    public  void setFrag1(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //프레그멘트 교체가 일어날때
        switch (n) {
            case 0:
                ft.replace(R.id.frame, TabFragment.neInstance());// TabFragment 레이아웃으로 교체
                //Frag1 으로 데이터를 전달하기위해 TabFragment의 newInstance메소드를 이용해 TabFragment로 전달

                ft.commit();
                break;
            case 1:
                ft.replace(R.id.frame, frag2);    //frag2 레이아웃으로 교체
                ft.commit();
                break;
        }
    }






}
