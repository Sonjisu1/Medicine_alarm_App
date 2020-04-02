package com.example.medicine_alarm;

public class TotalData {
    public String Medicinename;
   public String  Totalaccount;


   TotalData(String medicinename,String Totalaccount){
       this.Medicinename = medicinename;
       this.Totalaccount = Totalaccount;
   }
   public String getMedicinename(){
       return this.Medicinename;
   }

   public String getTotalaccount(){
       return this.Totalaccount;
   }





}
