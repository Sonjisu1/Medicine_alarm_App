package com.example.medicine_alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Total extends Fragment {

    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<TotalData> list;
    TotalAdapter adapter;

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
        list= new ArrayList<>();

        database=  FirebaseDatabase.getInstance(); // Firebase database 연동
        reference =database.getReference().child("medicine");// DB 테이블 연결


       /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    TotalData totaldata= snapshot.getValue(TotalData.class);

                    list.add(totaldata);
                   adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    TotalData totalData = new TotalData("dd","rr");
      Toast.makeText(getContext(),totalData.getTotalaccount()+"",Toast.LENGTH_SHORT).show();
      list.add(totalData);
        adapter = new TotalAdapter(list); //생성자를 이용해서 list를 Adapter로 전달
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //레이아웃형식

        recyclerView .setAdapter(adapter); //어뎁터 설정


        return view;

    }
}
