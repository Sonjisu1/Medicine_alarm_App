package com.example.medicine_alarm;

public class medicineitem {

    public int iconDrawable; //아이콘
    public String Medicinename; //약이름
    public String account;     //약개수
    public int hourofDay ; //현재 시
    public int minute; //현재 분



    public medicineitem(){

    }

    public medicineitem(int iconDrawable,String medicinename,String account,int hourofDay,int minute){
        this.iconDrawable=iconDrawable;
        this.Medicinename=medicinename;
        this.account=account;
        this.hourofDay=hourofDay;
       this.minute=minute;


    }
}
