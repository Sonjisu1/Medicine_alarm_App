package com.example.medicine_alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TotalquantitiyAdapter extends RecyclerView.Adapter<TotalquantitiyAdapter.ViewHolder> {

    ArrayList<TotalquantityData> mData = new ArrayList<>();
    // java.lang.NullPointerException: Attempt to invoke virtual method 'int java.util.ArrayList.size()' on a null object reference

    TotalquantitiyAdapter(ArrayList<TotalquantityData> list){
        mData=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.totalitem,parent,false);


        TotalquantitiyAdapter.ViewHolder vh = new TotalquantitiyAdapter.ViewHolder(view);
        //뷰홀더로 감쌈


        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TotalquantityData totalquantityData = mData.get(position);

        holder.Medicinename.setText(totalquantityData.getMedicinename());
        holder.Totalaccount.setText(totalquantityData.getTotalaccount());



    }

    @Override
    public int getItemCount() {
        return (mData !=  null ? mData.size() : 0);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Medicinename;
        TextView Totalaccount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Medicinename = itemView.findViewById(R.id.Medicinename);
            Totalaccount = itemView.findViewById(R.id.Totalaccount);
        }
    }
}
