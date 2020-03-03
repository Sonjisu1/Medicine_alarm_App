package com.example.medicine_alarm;

public class medicineitem {

    public int iconDrawable; //아이콘
    public String Medicinename; //약이름
    public String account;     //약개수


    public medicineitem(){

    }

    public medicineitem(int iconDrawable,String medicinename,String account){
        this.iconDrawable=iconDrawable;
        this.Medicinename=medicinename;
        this.account=account;

    }
}
