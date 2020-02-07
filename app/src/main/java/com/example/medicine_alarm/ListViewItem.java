package com.example.medicine_alarm;

//아이템 뷰에 표시될 데이터를 저장할 클래스 정의
public class ListViewItem {
    private int iconDrawable;  //Drawable
    private String titleStr ;
    private int descStr ;
    private  String titleStr1;

    public ListViewItem(){

    }

    public ListViewItem(int icon, String title, String title2, int  desc){
        this.iconDrawable = icon;
        this.titleStr = title;
        this.titleStr1 = title2;
        this.descStr = desc;

    }

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




}
