package local.karpowiczmajczyna.zarzad;

import local.karpowiczmajczyna.narzedzia.CzytaniePliku;
import local.karpowiczmajczyna.wyjatki.BladCzytaniaPliku;
import local.karpowiczmajczyna.wypozyczalnia.Pojazd;
import local.karpowiczmajczyna.wypozyczalnia.Rejestr;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Biuro implements CzytaniePliku<Wypozyczenie> {
    private final Rejestr rejestr;
    private final List<Wypozyczenie> wypozyczenia;

    public Biuro(Rejestr rejestr) {
        this.rejestr = rejestr;
        wypozyczenia = zaladujPlik();
    }

    @Override
    public List<Wypozyczenie> zaladujPlik() {
        List<Wypozyczenie> lista = new ArrayList<>();

        try {
            Scanner odczyt = new Scanner(new File("resources/wypozyczenia.txt"));

            while(odczyt.hasNextLine()) {
                String linia = odczyt.nextLine();
                String[] pola = linia.split(",");

                try {
                    Wypozyczenie wypozyczenie = mapuj(pola);
                    lista.add(wypozyczenie);
                } catch (Exception e) {
                    System.out.println("Nieznany pojazd o id " + pola[0]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Błąd ładowania pliku.");
            e.printStackTrace();
        };

        return lista;
    }

    @Override
    public Wypozyczenie mapuj(String[] pola) throws BladCzytaniaPliku {
        try {
            int id = Integer.parseInt(pola[0]);
            Pojazd pojazd = rejestr.znajdzPoId(id);
            String imie = pola[1];
            String nazwisko = pola[2];

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date data = format.parse(pola[3]);

            return new Wypozyczenie(pojazd, imie, nazwisko, data);
        } catch (ParseException e) {
            throw new BladCzytaniaPliku("Błędny format daty");
        } catch (NoSuchElementException e) {
            throw new BladCzytaniaPliku("Nieznany pojazd o id " + pola[0]);
        }
    }

    public List<Wypozyczenie> getWypozyczenia() {
        return wypozyczenia;
    }

    public
    Wypozyczenie znajdzPoIdPojazdu(int id) {
        return wypozyczenia.stream()
                .filter(wypozyczenie -> wypozyczenie.getPojazd().getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
