package com.example.medicine_alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Total extends Fragment {

    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerImageTextAdapter adapter;
    CalendarView calendar;
    ArrayList<ListViewItem> list;



    View view;

    public  static Total newInstance(){
       Total total= new Total();
        return total;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.total,container,false);

        RecyclerView recyclerView= view.findViewById(R.id.totalrecyclerview);

        list = new ArrayList<>();
        calendar = view.findViewById(R.id.calendarView);
        database=  FirebaseDatabase.getInstance(); // Firebase database 연동
        reference =database.getReference().child("medicine");// DB 테이블 연결

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                reference.child(year+"-"+(month+1)+"-"+dayOfMonth).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list.clear();

                        if(dataSnapshot.exists()){ //데이터가 없는 경우 Snapshot은 exists() 호출시 false 를 반환한다.
                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                ListViewItem listViewItem = snapshot.getValue(ListViewItem.class); //Firebase에서 데이터를 ListviewItem형태로 가져옴

                                    list.add(listViewItem); //Arraylist에 저장
                                    adapter.notifyDataSetChanged();//리스트 변경 적용
                                   // Toast.makeText(getContext(),"/"+year+"/"+(month+1)+"/"+dayOfMonth , Toast.LENGTH_SHORT).show();
                            }

                            }else{
                            list.clear(); //리스트 초기화
                            adapter.notifyDataSetChanged(); //리스트 변경 적용

                        }



                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        adapter = new RecyclerImageTextAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //레이아웃형식
        recyclerView.setAdapter(adapter); //어뎁터 설정*/





        return view;

    }
}
