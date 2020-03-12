package com.example.medicine_alarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddMedicine extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText edt1;
    TimePicker picker;
    int pre_hour; //이전 시간
    int pre_minute; //이전 분
    String pre_data; //이전 데이터
    String update_data;  //수정될 데이터
    Spinner spinner;
    medicineitem medicineitem;

    Bundle extras=null;

    private static int count=0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Button btnadd = (Button) findViewById(R.id.btnadd);
        edt1 = (EditText) findViewById(R.id.edt1);
        spinner = (Spinner) findViewById(R.id.spinner);

         picker = (TimePicker) findViewById(R.id.timepicker);
        //  Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();

        extras = getIntent().getExtras();  //MainActivity에서 보낸 이전 약 정보를 받음
        if (extras != null) {

            pre_data = extras.getString("name1"); //약 이름
            pre_hour=extras.getInt("hour");      //시
            pre_minute=extras.getInt("minute");  //분



            edt1.setText(pre_data);  //이전 약이름을 보여줌


        }
        database = FirebaseDatabase.getInstance(); // Firebase database 연동

        databaseReference = FirebaseDatabase.getInstance().getReference();


        // 앞서 설정한 값으로 보여주고 값이 없으면 현재 시간을 보여줌
       // SharedPreferences sharedPreferences = getSharedPreferences("daily alarm", MODE_PRIVATE);
       // long millis = sharedPreferences.getLong("nextNotifyTime", Calendar.getInstance().getTimeInMillis());
        long millis = Calendar.getInstance().getTimeInMillis();

        Calendar nextNotifyTime = new GregorianCalendar();
        nextNotifyTime.setTimeInMillis(millis);

        Date nextDate = nextNotifyTime.getTime();
        String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(nextDate);
        Toast.makeText(getApplicationContext(), "[처음 실행시] 다음 알람은 " + date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();


        // 현재시간을 보여줌
        Date currentTime = nextNotifyTime.getTime();
        SimpleDateFormat HourFormat = new SimpleDateFormat("kk", Locale.getDefault());
        SimpleDateFormat MinuteFormat = new SimpleDateFormat("mm", Locale.getDefault());

        int pre_hour1 = Integer.parseInt(HourFormat.format(currentTime));
        int pre_minute1 = Integer.parseInt(MinuteFormat.format(currentTime));

        if(extras!=null){  //리사이클러뷰 아이템 터치 시 실행됬다면
            if (Build.VERSION.SDK_INT >= 23) {
                picker.setHour(pre_hour);    //전에 지정했던 시간을 보여줌
                picker.setMinute(pre_minute); //전에 지정했던 시간을 보여줌
            } else {
                picker.setCurrentHour(pre_hour);
                picker.setCurrentMinute(pre_minute);
            }

        }else{            //약을 새로 추가한다면

            if (Build.VERSION.SDK_INT >= 23) {
                picker.setHour(pre_hour1);       //현재시간을 보여줌
                picker.setMinute(pre_minute1);   //현재시간을 보여줌
            } else {
                picker.setCurrentHour(pre_hour1);
                picker.setCurrentMinute(pre_minute1);
            }

        }



        btnadd.setOnClickListener(new View.OnClickListener() {  //저장 버튼 클릭 시
            @Override
            public void onClick(View v) {
           /*    Intent intent = new Intent(AddMedicine.this,MainActivity.class);
                intent.putExtra("name1",edt1.getText().toString());  ///MainActivity에 데이터 전달
                intent.putExtra("account",spinner.getSelectedItem().toString());

                startActivity(intent);*/
                int hour, hour_24, minute;

                String am_pm;
                if (Build.VERSION.SDK_INT >= 23) {
                    hour_24 = picker.getHour();
                    minute = picker.getMinute();

                } else {
                    hour_24 = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();

                }
                if (hour_24 > 12) {
                    am_pm = "PM";
                    hour = hour_24 - 12;
                } else {
                    hour = hour_24;
                    am_pm = "AM";
                }

                // 현재 지정된 시간으로 알람 시간 설정
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hour_24);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);

                // 이미 지난 시간을 지정했다면 다음날 같은 시간으로 설정
                if (calendar.before(Calendar.getInstance())) {
                    calendar.add(Calendar.DATE, 1);
                }

                Date currentDateTime = calendar.getTime();
                String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(currentDateTime);
                Toast.makeText(getApplicationContext(), date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();

                //  Preference에 설정한 값 저장
              //  SharedPreferences.Editor editor = getSharedPreferences("daily alarm", MODE_PRIVATE).edit();
                //editor.putLong("nextNotifyTime", (long) calendar.getTimeInMillis());
               // editor.apply();

                if (extras != null) {   //리사이클러뷰 아이템 터치 시 정보를 수정할 때


                    update_data = edt1.getText().toString(); //수정할 정보



                    databaseReference.child("medicine").child(pre_data).removeValue(); //기존 데이터 삭제

                    medicineitem medicineitem1 = new medicineitem(R.drawable.ic_access_alarm_black_24dp, update_data, spinner.getSelectedItem().toString(),hour_24,minute);
                    //새로운 데이터를 medicineitem 형태로

                    databaseReference.child("medicine").child(update_data).setValue(medicineitem1); // Firebase에 저장

                    finish();

                }else{  //수정이 아닌 버튼을 이용해 추가할 때
                    medicineitem medicineitem = new medicineitem(R.drawable.ic_access_alarm_black_24dp, edt1.getText().toString(), spinner.getSelectedItem().toString(),hour_24,minute);


                    databaseReference.child("medicine").child(edt1.getText().toString()).setValue(medicineitem);
                    finish();
                }

                diaryNotification(calendar);





            }
        });


    }

    public int createID() {  //다중 알람을 사용할 수 있도록 다른 id값 생성

        count++;
        return count;
    }


    void diaryNotification(Calendar calendar) {
//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        Boolean dailyNotify = sharedPref.getBoolean(SettingsActivity.KEY_PREF_DAILY_NOTIFICATION, true);
        Boolean dailyNotify = true; // 무조건 알람을 사용

        PackageManager pm = this.getPackageManager();
        ComponentName receiver = new ComponentName(this, DeviceBootReceiver.class);
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,createID() , alarmIntent, 0); //다중알람을 지원하기 위해 두번째 파라미터값을 각각 다르게 함
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //알람 매니저 설정



        // 사용자가 매일 알람을 허용했다면
        if (dailyNotify) {


            if (alarmManager != null) {

                //알람 반복 셋팅
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);
                //AlarmManager.RTC -> real time clock으로 실제 시간 기준으로 설정
                // calendar.getTimeInMillis -> 알람을 울릴 시간
                //AlarmManager.INTERVAL_DAY -> 다음 알람이 울리기 전까지의 시간

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
            }

            // 부팅 후 실행되는 리시버 사용가능하게 설정
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);

        }



    }
}