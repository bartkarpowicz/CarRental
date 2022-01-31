package local.karpowiczmajczyna.wypozyczalnia;

import local.karpowiczmajczyna.narzedzia.CzytaniePliku;
import local.karpowiczmajczyna.wyjatki.BladCzytaniaPliku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rejestr implements CzytaniePliku<Pojazd> {
    private final List<Pojazd> pojazdy;

    public Rejestr() {
        pojazdy = zaladujPlik();
    }

    @Override
    public List<Pojazd> zaladujPlik() {
        List<Pojazd> lista = new ArrayList<>();

        try {
            Scanner odczyt = new Scanner(new File("resources/pojazdy.txt"));

            while(odczyt.hasNextLine()) {
                String linia = odczyt.nextLine();
                String[] pola = linia.split(",");

                try {
                    Pojazd pojazd = mapuj(pola);
                    lista.add(pojazd);
                } catch (Exception e) {
                    System.out.println("Nieznana klasa pojazdu o id " + pola[0]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Błąd ładowania pliku.");
            e.printStackTrace();
        };

        return lista;
    }

    @Override
    public Pojazd mapuj(String[] pola) throws BladCzytaniaPliku {
        int id = Integer.parseInt(pola[0]);
        String marka = pola[1];
        String model = pola[2];
        int rocznik = Integer.parseInt(pola[3]);
        int oplata = Integer.parseInt(pola[4]);

        return switch (pola[5]) {
            case "s" -> new Samochod(id, marka, model, rocznik, oplata);
            case "m" -> new Motor(id, marka, model, rocznik, oplata);
            default -> throw new BladCzytaniaPliku("Nie ma takiej klasy");
        };
    }

    public List<Pojazd> getPojazdy() {
        return pojazdy;
    }

    public Pojazd znajdzPoId(int id) {
        return pojazdy.stream()
                .filter(pojazd -> pojazd.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
