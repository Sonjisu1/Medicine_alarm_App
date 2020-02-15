package com.example.medicine_alarm;

import android.os.Parcel;
import android.os.Parcelable;

//아이템 뷰에 표시될 데이터를 저장할 클래스 정의
public class ListViewItem implements Parcelable{
    private int iconDrawable;  //그림1
    private String titleStr ;  //약이름
    private int descStr ;        //개수
    private  String titleStr1; //그림2


    public ListViewItem(){

    }

    public ListViewItem(int icon, String title, String title2, int  desc){
        this.iconDrawable = icon;
        this.titleStr = title;
        this.titleStr1 = title2;
        this.descStr = desc;

    }

    protected ListViewItem(Parcel in) {
        iconDrawable = in.readInt();
        titleStr = in.readString();
        descStr = in.readInt();
        titleStr1 = in.readString();
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
        titleStr = title ;
    }
    public void setTitle1(String title1) {titleStr1 = title1;}
    public void setDesc(int desc) {
        descStr = desc ;
    }

    public int getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public int getDesc() {
        return this.descStr ;
    }
    public String getTitle1() {return this.titleStr1;}


    @Override
    public int describeContents() {
        return 0;
    }

    //객체 전달할 때 호출
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleStr);
        dest.writeString(titleStr1);
        dest.writeInt(iconDrawable);
        dest.writeInt(descStr);
    }
}
