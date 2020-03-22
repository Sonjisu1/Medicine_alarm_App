package com.example.medicine_alarm;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

public class CustomDialog extends Dialog {
    private View.OnClickListener mDelayListener;
    private View.OnClickListener mSsListener;
    String name;

    public CustomDialog(@NonNull Context context, View.OnClickListener mDelayListener, View.OnClickListener mSsListener,String name)
     {
        super(context);
        this.mDelayListener = mDelayListener;
        this.mSsListener=mSsListener;
        this.name=name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.custom_dialog);

        TextView medicinename = (TextView) findViewById(R.id.medicinename);
        Button cancel = (Button)findViewById(R.id.delay);
        Button ss = (Button) findViewById(R.id.ss);

        cancel.setOnClickListener(mDelayListener); //클릭 리스너
        ss.setOnClickListener(mSsListener);   //클릭 리스너
        medicinename.setText(name); //AlarmReceiver에서 받은 약 이름 데이터를 보여줌





    }


}
