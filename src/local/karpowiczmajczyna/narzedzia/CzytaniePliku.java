package local.karpowiczmajczyna.narzedzia;

import local.karpowiczmajczyna.wyjatki.BladCzytaniaPliku;

import java.util.List;

public interface CzytaniePliku<T> { //Interfejs generyczny/ T - zastępuje klasę docelową
    public List<T> zaladujPlik();
    public T mapuj(String[] pola) throws BladCzytaniaPliku;
}
