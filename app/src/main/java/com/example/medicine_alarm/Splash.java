package com.example.medicine_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 2000);

    }

    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(),MainActivity.class));
            //로딩이 끝난 후 MainActivity로 이동
            Splash.this.finish(); //로딩페이지 Acitivity stack에서 제거
        }

    }

    public void onBackPressed(){
        //splash화면에서 뒤로가기 버튼 못누르게
    }
}
