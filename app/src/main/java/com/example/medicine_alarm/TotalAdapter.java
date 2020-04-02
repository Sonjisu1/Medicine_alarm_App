package com.example.medicine_alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.ViewHolder> {


    ArrayList<TotalData> mData = new ArrayList<TotalData>() ;

    TotalAdapter(){

    }
    TotalAdapter(ArrayList<TotalData> list){
        mData=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.totalitem,parent,false);


        TotalAdapter.ViewHolder vh = new TotalAdapter.ViewHolder(view);
        //뷰홀더로 감쌈


        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TotalData totalData = mData.get(position);

        holder.Medicinename.setText(totalData.getMedicinename());
        holder.Totalaccount.setText(totalData.getTotalaccount());



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Medicinename;
        TextView Totalaccount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Medicinename = itemView.findViewById(R.id.medicineName);
            Totalaccount= itemView.findViewById(R.id.account);
        }
    }
}
