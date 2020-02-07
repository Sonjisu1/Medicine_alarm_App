package com.example.medicine_alarm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Frag1 extends Fragment {

    View view;



    RecyclerView recyclerView;
    RecyclerImageTextAdapter recyclerImageTextAdapter;
    public ArrayList<ListViewItem> list = new ArrayList<>();    // 먹을 약 알람 리스트 데이터 저장


    public  static Frag1 newInstance(){
        Frag1 frag1 = new Frag1();
        return frag1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showItemList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag1,container,false); //레이아웃 지정
        Context context = view.getContext(); //Context가져오기

        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView2); //달력
        //TextView MonthDay = (TextView) view.findViewById(R.id.calendar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclelist);


        recyclerImageTextAdapter = new RecyclerImageTextAdapter(context,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //레이아웃형식

        recyclerView.setAdapter(recyclerImageTextAdapter); //어뎁터 설정

        addItem("타이레놀"); //아이템 추가


        return view;
    }

    public void showItemList(){           //처음에만 제대로 나오고 그 후에는 제대로 나오지 않아서 추가
        recyclerImageTextAdapter = new RecyclerImageTextAdapter(getContext(),list);
        recyclerView.setAdapter(recyclerImageTextAdapter);
    }


//리사이클러뷰 아이템 추가 함수
    public void addItem ( String title){

        ListViewItem item = new ListViewItem();

        item.setTitle(title);                         //데이터 지정
        item.setTitle1("2알");
        item.setDesc(R.drawable.ic_person_black_24dp);
        item.setIcon(R.drawable.ic_delete_black_24dp);


        list.add(item);           //데이터 추가


    }

}
