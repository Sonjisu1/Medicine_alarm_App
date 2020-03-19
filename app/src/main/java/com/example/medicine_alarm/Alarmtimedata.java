package com.example.medicine_alarm;

public class Alarmtimedata {

    public String hour ; //현재 시

    public String ampm ; //오전/오후

    public String mintue01;



    public Alarmtimedata(){

    }

    public Alarmtimedata( String hourofDay,String ampm,String mintue01){

        this.hour=hourofDay;
        this.mintue01=mintue01;
        this.ampm=ampm;


    }

    public void sethour(String hourofDay) {
        this.hour = hourofDay;
    }

 public void setMintue01(String mintue01){
        this.mintue01=mintue01;
 }

    public String getAmpm(){return this.ampm;}

    public String getMintue01(){
        return this.mintue01;
    }
    public String gethour() {
        return this.hour;
    }


}
