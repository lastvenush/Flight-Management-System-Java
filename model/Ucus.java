package model;

import java.util.ArrayList;
import java.util.List;

public class Ucus {

    private String ucusKodu;
    private String kalkis;
    private String varis;
    private String sirket; // thy, ajet, pegasus
    private List<Yolcu> yolcular = new ArrayList<>();

    public Ucus(String ucusKodu, String kalkis, String varis, String sirket) {
        this.ucusKodu = ucusKodu;
        this.kalkis = kalkis;
        this.varis = varis;
        this.sirket = sirket;
    }

    public void yolcuEkle(Yolcu y) {
        yolcular.add(y);
    }

    public void yolcuSil(Yolcu y) {
        yolcular.remove(y);
    }

    public String getUcusKodu() { return ucusKodu; }
    public String getKalkis() { return kalkis; }
    public String getVaris() { return varis; }
    public String getSirket() { return sirket; }
    public List<Yolcu> getYolcular() { return yolcular; }

    @Override
    public String toString() {
        return ucusKodu + " (" + kalkis + " → " + varis + ")";
    }
}
