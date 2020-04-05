package com.example.medicine_alarm;

public class TotalquantityData {
    private String Medicinename; //약 이름
    private String Totalaccount;  // 총 수량

    TotalquantityData(){

    }

    public void setMedicinename(String medicinename) {
        this.Medicinename = medicinename;
    }

    public void setTotalaccount(String totalaccount) {
        this.Totalaccount = totalaccount;
    }

    public String getMedicinename() {
        return this.Medicinename;
    }

    public String getTotalaccount() {
        return this.Totalaccount;
    }
}
