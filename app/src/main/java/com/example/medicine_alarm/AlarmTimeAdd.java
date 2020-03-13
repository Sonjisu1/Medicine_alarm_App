package com.example.medicine_alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlarmTimeAdd extends RecyclerView.Adapter<AlarmTimeAdd.ViewHolder> {

    ArrayList<Alarmtimedata> mData= new ArrayList<>();

    public AlarmTimeAdd(){

    }
    public AlarmTimeAdd(ArrayList<Alarmtimedata> list){
        mData=list;
    }

    @NonNull
    @Override
    public AlarmTimeAdd.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_alarmtime,parent,false);


       AlarmTimeAdd.ViewHolder vh = new AlarmTimeAdd.ViewHolder(view);
        //뷰홀더로 감쌈


        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull AlarmTimeAdd.ViewHolder holder, final int position) {

        Alarmtimedata item = mData.get(position);



        holder.hour.setText(item.gethour());
        holder.minute.setText(item.getMinute());
        holder.ampm.setText(item.getAmpm());
        Button delbtn = holder.delbtn;

        /*delbtn.setOnClickListener(new View.OnClickListener() { //아이템 안에 버튼 클릭 시 아이템 삭제
            @Override
            public void onClick(View v) {
                mData.remove(position);
            }
        });*/



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button delbtn;
        TextView ampm;
        TextView minute;
        TextView hour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            delbtn=itemView.findViewById(R.id.delbtn);
            minute= itemView.findViewById(R.id.minute);
            hour = itemView.findViewById(R.id.hour);
            ampm=itemView.findViewById(R.id.ampm);



        }
    }
}
