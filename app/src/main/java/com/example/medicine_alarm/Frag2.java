package com.example.medicine_alarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag2 extends Fragment {

    View view;
    Button noticebtn;

    public  static Frag2 newInstance(){
        Frag2 frag2 = new Frag2();
        return frag2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag2,container,false);



         noticebtn = (Button) view.findViewById(R.id.noticebtn);

         noticebtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(),NoticeActivity.class);
                 startActivity(intent);
             }
         });

        return view;

    }
}
