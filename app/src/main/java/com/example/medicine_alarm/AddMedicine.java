package com.example.medicine_alarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddMedicine extends Fragment {
    EditText edt1;
    View view;

    //MainActivity에게 데이터를 전달하기위한 interface구현
    public interface onClickListenr{
        void onInputedData(String name);
    }



    private onClickListenr mCallback;

    public static AddMedicine newInstance(){
        AddMedicine addMedicine = new AddMedicine();
        return addMedicine;
    }



    //MainActivity에서 구현한 interface와 연결
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
       // mCallback = (onClickListenr) context;
       if(getActivity() != null && getActivity() instanceof  onClickListenr){
           mCallback = (onClickListenr) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_add_medicine,container,false);

        Button btnadd =(Button)view.findViewById(R.id.btnadd);
        edt1 = (EditText)view.findViewById(R.id.edt1);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCallback !=null)
                mCallback.onInputedData(edt1.getText().toString());
              /* FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(AddMedicine.this).commit();
               fragmentManager.popBackStack();
*/

            }
        });


        return view;

    }



}
