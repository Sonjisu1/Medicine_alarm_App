package com.example.medicine_alarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AlarmReceiver extends BroadcastReceiver {

    String name;



    @Override
    public void onReceive(Context context, Intent intent) {

        /*SharedPreferences sharedPreferences = context.getSharedPreferences("IdFile",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("id",0);
        editor.commit();*/
        //Notification 표시
        Bundle bundle = intent.getExtras(); //alarmIntent에서 보낸 데이터 전달받음
        if(bundle != null){
            name = bundle.getString("medicinename");
        }

        Toast.makeText(context,"도착",Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Notification 클릭 시 이동할 class
        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        notificationIntent.putExtra("medicine", name); //알람이 설정된 약 이름을 MainActivity로 값을 전달


        PendingIntent pendingI = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);


        //Notification 클릭 시 Acitivity 실행을 위해 필요


        //콘텐츠와 채널 설정

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");



        //OREO API 26 이상에서는 채널 필요
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남


            String channelName ="매일 알람 채널";
            String description = "매일 정해진 시간에 알람합니다.";
            int importance = NotificationManager.IMPORTANCE_HIGH; //소리와 알림메시지를 같이 보여줌

            NotificationChannel channel = new NotificationChannel("default", channelName, importance);
            channel.setDescription(description);

            if (notificationManager != null) { //채널이 없으면
                // 노티피케이션 채널을 시스템에 등록
                notificationManager.createNotificationChannel(channel);
            }
        }else builder.setSmallIcon(R.mipmap.ic_launcher); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남


        builder.setAutoCancel(true) //Notification 터치 시 사라짐
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())

                .setTicker("{Time to watch some cool stuff!}")
                .setContentTitle(name+" 드실 시간입니다.") //보여질 타이틀
                .setContentText("클릭 시 앱으로 이동합니다.") //타이틀 아래에 보이는 텍스트
                .setContentInfo("INFO")

                .setContentIntent(pendingI); // 앞에서 만든 pendingl을 Notification에 등록

        if (notificationManager != null) {

            //휴대폰이 꺼져있어도 Notification이 작동하도록
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK  |
                    PowerManager.ACQUIRE_CAUSES_WAKEUP |
                    PowerManager.ON_AFTER_RELEASE, "My:Tag");
            wakeLock.acquire(5000);

            // 노티피케이션 등록
            notificationManager.notify(1234, builder.build());

          //  context.startActivity(notificationIntent);

            Calendar nextNotifyTime = Calendar.getInstance();

            // 내일 같은 시간으로 알람시간 결정
            nextNotifyTime.add(Calendar.DATE, 1);

            //  Preference에 설정한 값 저장
           // SharedPreferences.Editor editor = context.getSharedPreferences("daily alarm", Context.MODE_PRIVATE).edit();
           // editor.putLong("nextNotifyTime", nextNotifyTime.getTimeInMillis());
          //  editor.apply();

            Date currentDateTime = nextNotifyTime.getTime();
            String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(currentDateTime);
           // Toast.makeText(context.getApplicationContext(),"다음 알람은 " + date_text + "으로 알람이 설정되었습니다!", Toast.LENGTH_SHORT).show();
        }

    }





}
