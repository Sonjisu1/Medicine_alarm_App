package com.example.medicine_alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Frag1 extends Fragment {

    View view;
  ListViewItem Medname;
    FirebaseDatabase database;
    DatabaseReference reference;



    private FragmentManager fm;
    private FragmentTransaction ft;
    RecyclerView recyclerView;


    RecyclerImageTextAdapter recyclerImageTextAdapter;
    public ArrayList<ListViewItem> list = new ArrayList<>();    // 먹을 약 알람 리스트 데이터 저장

    public Frag1(){

    }

    //AddActivity에게 데이터를 전달하기위한 interface구현
    public interface onClickListenr{
        void onInputedData(String name,int pre_hour,int pre_minute);
    }

    private onClickListenr mCallback;



    //MainActivity에서 구현한 인터페이스와 연결
    //Fragment는 종속된 Activity를 통해서 다른 Activity나 Fragment와 통신하기
    //때문에 먼저 MainActivity로 데이터를 전달
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // mCallback = (onClickListenr) context;
        if(getActivity() != null && getActivity() instanceof  onClickListenr){
            mCallback = (onClickListenr) getActivity();
        }
    }
    //연결을 끊기위한 메소드
    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }



    //arguments 를 전달하는 프레그먼트 객체 생성 메소드
    // newInstance 메소드를 통해 파라미터로 필요한 데이터를 전달하고 Intent를 통해 데이터를 넘겨줌
    public  static Frag1 newInstance(){
        Frag1 frag1 = new Frag1();
       /* Bundle args = new Bundle();
        args.putParcelable("list",list);   //list는 ListviewItem형태
        //args.putString("name1",name);
       //args.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) list);
        frag1.setArguments(args);*/
        return frag1;       // fragment return
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showItemList();

       /* if(Medname != null){

            ListViewItem item = new ListViewItem();
            item.setTitle(Medname.getTitle());
            item.setTitle1(Medname.getTitle1());
            item.setIcon(Medname.getIcon());


            list.add(item);
            recyclerImageTextAdapter.notifyDataSetChanged();
            Medname =null;

        }*/
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Medname = getArguments().getString("name1");
        //Medname = getArguments().getParcelable("list");
        //newInstacne에서 전달된 bundle데이터를 getArguments()를 통해 받음

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag1,container,false); //레이아웃 지정

        database=  FirebaseDatabase.getInstance(); // Firebase database 연동
        reference =database.getReference("medicine");// DB 테이블 연결




        ImageButton imageButton = (ImageButton) view.findViewById(R.id.add);
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView2); //달력
        TextView MonthDay = (TextView) view.findViewById(R.id.calendar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclelist);


      //if(Medname !=null)
       // Toast.makeText(getContext(),"frage 전달",Toast.LENGTH_SHORT).show();


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    ListViewItem listViewItem = snapshot.getValue(ListViewItem.class); //Firebase에서 데이터를 ListviewItem형태로 가져옴
                            list.add(listViewItem); //Arraylist에 저장
                    recyclerImageTextAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerImageTextAdapter = new RecyclerImageTextAdapter(list); //생성자를 이용해서 list를 Adapter로 전달
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //레이아웃형식

        recyclerView .setAdapter(recyclerImageTextAdapter); //어뎁터 설정



       /* if(Medname != null){   //아이템 추가

            addItem(Medname);
        }
*/





        //리사이클러뷰 클릭 이벤트
        //리사이클러뷰 클릭 시 편집을 위해서 이전 입력했던 데이터가 뜨게 함
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {//약 수정을 위해서 클릭 시 이전 데이터가 나오게 함


                ListViewItem item = list.get(position);


                //list에 저장된 데이터를 MainActivity로 보냄
                if(mCallback !=null) {
                    mCallback.onInputedData(item.getTitle(),item.getHourofDay(),item.getMinute()); //기존의 이름과 시간
                }

               /*Intent intent = new Intent(getActivity(), AddMedicine.class);

                startActivity(intent);*/


                // Toast.makeText(getContext(),"클릭",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {

                ListViewItem item = list.get(position); //현재 롱클릭한 아이템 위치
                list.remove(position); //리사이클러뷰 아이템 삭제
                recyclerImageTextAdapter.notifyItemRemoved(position); //리사이클러뷰에 반영
                reference.child(item.getTitle()).removeValue(); //Firebase에 데이터 삭제



            }
        }));



        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd hh:kk");
        Date time = new Date(); //DATE 객체선언

        String time1 = format1.format(time); //날짜 시간 출력

        MonthDay.setText(time1); // 년 날짜 시간 출력


        //버튼 클릭 시 약 추가 화면으로 전환
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//먹을약 생성


             Intent intent = new Intent(getActivity(),AddMedicine.class);  //frgment에서는 this를 쓸수 없기 때문에

                //Acitivity의 참조를 얻어오기 위해서 getActivity()를사용한다.
                startActivity(intent);


                //Toast.makeText(getContext(),count,Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    public void showItemList(){           //처음에만 제대로 나오고 그 후에는 제대로 나오지 않아서 추가
        recyclerImageTextAdapter = new RecyclerImageTextAdapter(list);
        recyclerView.setAdapter(recyclerImageTextAdapter);
    }




    //리사이클러뷰 클릭 이벤트를 위한 인터페이스 및 클래스
    public interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view,int position);


    }
    public  static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public  RecyclerTouchListener(Context context,final RecyclerView recyclerView,final ClickListener clickListener){
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
