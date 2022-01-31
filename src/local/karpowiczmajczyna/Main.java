package local.karpowiczmajczyna;

import local.karpowiczmajczyna.wypozyczalnia.Motor;
import local.karpowiczmajczyna.wypozyczalnia.Pojazd;
import local.karpowiczmajczyna.wypozyczalnia.Rejestr;
import local.karpowiczmajczyna.wypozyczalnia.Samochod;
import local.karpowiczmajczyna.zarzad.Biuro;
import local.karpowiczmajczyna.zarzad.Wypozyczenie;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static Rejestr rejestr;
    private static Biuro biuro;

    public static void main(String[] args) {
        rejestr = new Rejestr();
        biuro = new Biuro(rejestr);


        System.out.println("Baza wypożyczalni jest gotowa do pracy!");
        System.out.println("Co chcesz wyświetlić ? (wybierz numer przypisany do czynności i zatwierdź poprzez klawisz Enter)");
        System.out.println("1. Lista pojazdów wypożyczalni\n2. Lista samochodów wypożyczalni" +
                "\n3. Lista klientów wypożyczających\n4. Lista wypozyczonych pojazdów\n5. Sprawdź stan konkretnego pojazdu");

        Scanner scan = new Scanner(System.in);
        Integer wybor = scan.nextInt();
        switch(wybor){
            case 1: wypiszPojazdy();
                break;
            case 2: wypiszSamochody();
                break;
            case 3: wypiszMotory();
                break;
            case 4: wypiszWypozyczonePojazdy();
                break;
            case 5: Scanner scann = new Scanner(System.in);
                    System.out.println("Podaj id pojazdu, który chcesz sprawdzić");
                    Integer id = scan.nextInt();
                    sprawdzCzyWypozyczony(id);
                break;

        }

        System.out.println("--------------------");

    }

    private static void wypiszPojazdy() {
        for(Pojazd pojazd : rejestr.getPojazdy()) {
            System.out.println(pojazd);
        }
    }

    private static void wypiszSamochody() {
        for(Pojazd pojazd : rejestr.getPojazdy()) {
            if (pojazd instanceof Samochod) {
                System.out.println(pojazd);
            }
        }
    }
    private static void wypiszMotory() {
        for(Pojazd pojazd : rejestr.getPojazdy()) {
            if (pojazd instanceof Motor) {
                System.out.println(pojazd);
            }
        }
    }
    private static void wypiszWypozyczonePojazdy() {
        for(Wypozyczenie wypozyczenie : biuro.getWypozyczenia()) {
            System.out.println(wypozyczenie);
        }
        System.out.println("Kwota podana na końcu jest to koszt wypożyczenia (do dzisiejszego dnia)");
    }
    private static void sprawdzCzyWypozyczony(int id){
        try {
            biuro.znajdzPoIdPojazdu(id);
            System.out.println("Wypożyczony");
        } catch (NoSuchElementException e) {
            System.out.println("Wolny");
        }
    }




}
