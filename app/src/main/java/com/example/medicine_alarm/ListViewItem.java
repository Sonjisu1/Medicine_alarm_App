package com.example.medicine_alarm;

import android.os.Parcel;
import android.os.Parcelable;

//아이템 뷰에 표시될 데이터를 저장할 클래스 정의
public class ListViewItem implements Parcelable{
    public int iconDrawable; //아이콘
    public String Medicinename; //약이름
    public String account;     //약개수 //개수
    public int hourofDay ; //현재 시
    public int minute; //현재 분


    public ListViewItem(){

    }

    public ListViewItem(int icon, String title, String title2,String account,int hourofDay,int minute){
        this.iconDrawable = icon;
        this.Medicinename = title;
        this.account = title2;
        this.hourofDay=hourofDay;
        this.minute=minute;
    }



    protected ListViewItem(Parcel in) {
        iconDrawable = in.readInt();
       Medicinename= in.readString();

        account = in.readString();
    }

    public static final Creator<ListViewItem> CREATOR = new Creator<ListViewItem>() {
        @Override
        public ListViewItem createFromParcel(Parcel in) {
            return new ListViewItem(in);
        }

        @Override
        public ListViewItem[] newArray(int size) {
            return new ListViewItem[size];
        }
    };

    public void setIcon(int icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
       Medicinename = title ;
    }
    public void setTitle1(String title1) {account = title1;}
    public void setHourofDay(int hourofDay){
        this.hourofDay =hourofDay;
    }

    public void setMinute(int minute){
        this.minute=minute;
    }

    public int getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.Medicinename ;
    }

    public String getTitle1() {return this.account;}
   public int getHourofDay(){
        return this.hourofDay;
    }
    public int getMinute(){
        return this.minute;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    //객체 전달할 때 호출
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Medicinename);
        dest.writeString(account);
        dest.writeInt(iconDrawable);

    }
}
