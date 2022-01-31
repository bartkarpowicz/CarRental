package local.karpowiczmajczyna.wypozyczalnia;

public abstract class Pojazd {
    private final int id;
    private final String marka;
    private final String model;
    private final int rocznik;
    private final int oplata;

    public Pojazd(int id, String marka, String model, int rocznik, int oplata) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.rocznik = rocznik;
        this.oplata = oplata;
    }

    public int getId() {
        return id;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getRocznik() {
        return rocznik;
    }

    public int getOplata() {
        return oplata;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + marka + " " + model + " " + rocznik + " - " + oplata + "PLN/24h";
    }
}
