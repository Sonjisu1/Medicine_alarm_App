package com.example.medicine_alarm;

public class Alarmtimedata {

    public String hour ; //현재 시
    public String minute; //현재 분
    public String ampm ; //오전/오후



    public Alarmtimedata(){

    }

    public Alarmtimedata( String hourofDay, String minute,String ampm){

        this.hour=hourofDay;
       this.minute=minute;
       this.ampm=ampm;


    }

    public void sethour(String hourofDay) {
        this.hour = hourofDay;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
    public String getAmpm(){return this.ampm;}

    public String getMinute() {
        return this.minute;
    }

    public String gethour() {
        return this.hour;
    }


}
