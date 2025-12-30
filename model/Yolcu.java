package model;

public class Yolcu {

    private String ad;
    private String soyad;

    public Yolcu(String ad, String soyad) {
        this.ad = ad;
        this.soyad = soyad;
    }

    @Override
    public String toString() {
        return ad + " " + soyad;
    }
}
