package com.example.medicine_alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(@NonNull final AlarmTimeAdd.ViewHolder holder, final int position) {

        Alarmtimedata item = mData.get(position);



        holder.hour.setText(item.gethour());
        holder.minute.setText(item.getMintue01());

        holder.ampm.setText(item.getAmpm());




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView ampm;
        TextView minute;
        TextView hour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            minute= itemView.findViewById(R.id.minute);
            hour = itemView.findViewById(R.id.hour);
            ampm=itemView.findViewById(R.id.ampm);



        }
    }
}
