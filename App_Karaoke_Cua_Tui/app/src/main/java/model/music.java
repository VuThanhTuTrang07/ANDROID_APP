package model;

import com.vttt.vuthanhtutrang.app_karaoke_cua_tui.MainActivity;

public class music {
    private String ma;
    private String ten;
    private String loibh;
    private String casi;
    public int thich = 0;




    public music(String ma, String ten,String loibh, String casi, int thich) {
        this.ma = ma;
        this.ten = ten;
        this.loibh = loibh;
        this.casi = casi;
        this.thich = thich;
    }

    public music() {

    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return MainActivity.removeAccent(ten);
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getLoibh() {
        return loibh;
    }

    public void setLoibh(String loibh) {
        this.loibh = loibh;
    }

    public String getCasi() {
        return casi;
    }

    public void setCasi(String casi) {
        this.casi = casi;
    }

    public int getThich() {
        return thich;
    }

    public void setThich(int thich) {
        this.thich = thich;
    }


}
