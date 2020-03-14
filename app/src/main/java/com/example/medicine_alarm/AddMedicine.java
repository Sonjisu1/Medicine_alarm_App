package com.example.medicine_alarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
    int  hour_24, minute;
    int hour;
    AlarmManager alarmManager; //알람매니저
    PendingIntent pendingIntent;

    ArrayList<Alarmtimedata> list = new ArrayList<>(); //알람시간 추가 데이터를 저장할 Arraylist

    Bundle extras=null;
    String am_pm;
    private static int count=0;
    AlarmTimeAdd adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        Button btnadd = (Button) findViewById(R.id.btnadd);
        edt1 = (EditText) findViewById(R.id.edt1);
        spinner = (Spinner) findViewById(R.id.spinner);
        Button timeadd = (Button) findViewById(R.id.timeAdd);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.addalarmtime);

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
       // String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(nextDate);
       // Toast.makeText(getApplicationContext(), " 알람은 " + date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();


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

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),recyclerView, new ClickListener(){

            @Override
            public void onClick(View view, int position) {

                list.remove(position); //리사이클러뷰 아이템 삭제
                adapter.notifyItemRemoved(position); //리사이클러뷰에 반영
                cancelAlarm(); //알람(Notification) 취소
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        timeadd.setOnClickListener(new View.OnClickListener() { //알람시간 추가하기 버튼 클릭 시
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {    //Timepicker에 설정된 시간 가져오기
                    hour_24 = picker.getHour();
                    minute = picker.getMinute();

                } else {
                    hour_24 = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();

                }
                if (hour_24 > 12) {       //오전 오후 가져오기
                    am_pm = "오후";
                    hour = hour_24 - 12;
                } else {
                    hour = hour_24;
                    am_pm = "오전";
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
                Alarmtimedata alarmtimedata= new Alarmtimedata(hour_24+":",minute+"",am_pm); //시간 데이터 저장

                list.add(alarmtimedata);   //데이터를 ArrayList에 저장





                adapter.notifyDataSetChanged(); //데이터 변경 알려줌

                diaryNotification(calendar);






            }
        });


        adapter = new AlarmTimeAdd(list);//생성자를 이용해서 list를 Adapter로 전달

        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //레이아웃형식
        recyclerView.setAdapter(adapter); //어뎁터 설정






        btnadd.setOnClickListener(new View.OnClickListener() {  //저장 버튼 클릭 시
            @Override
            public void onClick(View v) {
           /* Intent intent = new Intent(AddMedicine.this,MainActivity.class);
                intent.putExtra("name1",edt1.getText().toString());  ///MainActivity에 데이터 전달
                intent.putExtra("account",spinner.getSelectedItem().toString());

                startActivity(intent);*/



               /* if (Build.VERSION.SDK_INT >= 23) {
                    hour_24 = picker.getHour();
                    minute = picker.getMinute();

                } else {
                    hour_24 = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();

                }
                if (hour_24 > 12) {
                    am_pm = "오후";
                    hour = hour_24 - 12;
                } else {
                    hour = hour_24;
                    am_pm = "오전";
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
*/
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

                    if(edt1.getText().toString() !=null){

                        medicineitem medicineitem = new medicineitem(R.drawable.ic_access_alarm_black_24dp, edt1.getText().toString(), spinner.getSelectedItem().toString(),hour_24,minute);


                        databaseReference.child("medicine").child(edt1.getText().toString()).setValue(medicineitem);
                        finish();

                       Toast.makeText(getApplicationContext(),"알람이 설정되었습니다.",Toast.LENGTH_SHORT).show();


                    }else{ //복용할 약 이름을 적지않았을 때
                        Toast.makeText(getApplicationContext(),"복용할 약 이름을 적어주세요",Toast.LENGTH_SHORT).show();
                    }

                }

               // diaryNotification(calendar);



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
         pendingIntent = PendingIntent.getBroadcast(this,createID() , alarmIntent, 0); //다중알람을 지원하기 위해 두번째 파라미터값을 각각 다르게 함
         alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
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

    void cancelAlarm(){  //알람 취소를 위한 함수
        alarmManager.cancel(pendingIntent);

    }


    //리사이클러뷰 클릭 이벤트를 위한 인터페이스 및 클래스
    public interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view,int position);


    }
    public  static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private AddMedicine.ClickListener clickListener;

        public  RecyclerTouchListener(Context context,final RecyclerView recyclerView,final AddMedicine.ClickListener clickListener){
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

                public boolean onSingleTapUp(MotionEvent e){
                    return true;
                }
                public void onLongPress(MotionEvent e){
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child != null && clickListener !=null){
                        clickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }



        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(),e.getY());
            if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}