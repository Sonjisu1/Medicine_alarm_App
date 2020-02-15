package com.example.medicine_alarm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ArrayListSend implements Parcelable {
    //ArrayList를 전달하기 위해서 Parcelable 사용
    //직접 만든 객체인 ArrayList<ListViewItem>를 전달하기 위한 class

    ArrayList<ListViewItem> list;

    public ArrayListSend(){

    }

    protected ArrayListSend(Parcel in) {
        list = new ArrayList<>();
        in.readTypedList(list,ListViewItem.CREATOR);
    }

    public static final Creator<ArrayListSend> CREATOR = new Creator<ArrayListSend>() {
        @Override
        public ArrayListSend createFromParcel(Parcel in) {
            return new ArrayListSend(in);
        }

        @Override
        public ArrayListSend[] newArray(int size) {
            return new ArrayListSend[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(list);
    }

    public ArrayList<ListViewItem> getListViewItem(){
        return list;
    }

    public void setListViewItem(ArrayList<ListViewItem> list){
        this.list = list;
    }


}
