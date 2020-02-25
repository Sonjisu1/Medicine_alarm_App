package com.example.medicine_alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    ArrayList<String> mData =null;

    //생성자에서 list객체를 전달받음
    public NoticeAdapter (ArrayList<String> list){
        mData = list;
    }



    @NonNull
    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.notice_item,parent,false);


        NoticeAdapter.ViewHolder vh = new NoticeAdapter.ViewHolder(view);
        //뷰홀더로 감쌈


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.ViewHolder holder, int position) {
        String text = mData.get(position); // mData에 들어있는 값 가져오기
        holder.textView.setText(text); //textview에 값 올리기
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            textView = itemView.findViewById(R.id.noticetext);


        }
    }
}
