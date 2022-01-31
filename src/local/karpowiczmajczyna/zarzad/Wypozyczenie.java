package local.karpowiczmajczyna.zarzad;

import local.karpowiczmajczyna.wypozyczalnia.Pojazd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Wypozyczenie {
    private final Pojazd pojazd;
    private final String imie;
    private final String nazwisko;
    private final Date dataWypozyczenia;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public Wypozyczenie(Pojazd pojazd, String imie, String nazwisko, Date dataWypozyczenia) {
        this.pojazd = pojazd;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public Pojazd getPojazd() {
        return pojazd;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    @Override
    public String toString() {
        return "(" + pojazd.getId() + ") " + pojazd.getMarka() + " " +
                pojazd.getModel() + " " + pojazd.getRocznik() + " - " +
                imie + " " + nazwisko + " " + formatter.format(dataWypozyczenia) + " " + wyliczKoszt() + "PLN";
    }

    private int wyliczKoszt() {
        long roznicaMilisekundy = Math.abs(dataWypozyczenia.getTime() - new Date().getTime());
        long ileDni = TimeUnit.DAYS.convert(roznicaMilisekundy, TimeUnit.MILLISECONDS);

        return (int) (ileDni * pojazd.getOplata());
    }
}