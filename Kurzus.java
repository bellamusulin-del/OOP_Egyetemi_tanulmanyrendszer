package tanulmanyi;

import java.util.ArrayList;
import java.util.List;

public class Kurzus {
    private final String nev;
    private final Oktato oktato;
    private final int kredit;
    private final int maxLetszam;
    private final List<Beiratkozas> beiratkozasok = new ArrayList<>();

    public Kurzus(String nev, Oktato oktato, int kredit, int maxLetszam) {
        if (nev == null || nev.isBlank()) {
            throw new IllegalArgumentException("A kurzus neve kotelezo.");
        }
        if (oktato == null) {
            throw new IllegalArgumentException("Az oktato kotelezo.");
        }
        if (kredit <= 0 || maxLetszam <= 0) {
            throw new IllegalArgumentException("A kredit es a letszam legyen pozitiv.");
        }
        this.nev = nev;
        this.oktato = oktato;
        this.kredit = kredit;
        this.maxLetszam = maxLetszam;
    }

    public String getNev() {
        return nev;
    }

    public Oktato getOktato() {
        return oktato;
    }

    public int getKredit() {
        return kredit;
    }

    public int getMaxLetszam() {
        return maxLetszam;
    }

    public List<Beiratkozas> getBeiratkozasok() {
        return List.copyOf(beiratkozasok);
    }

    void hozzaadBeiratkozas(Beiratkozas beiratkozas) {
        beiratkozasok.add(beiratkozas);
    }

    public double getAtmenesiArany() {
        long ertekelt = beiratkozasok.stream()
                .filter(b -> b.getJegy().isPresent())
                .count();
        if (ertekelt == 0) {
            return 0.0;
        }
        long atment = beiratkozasok.stream()
                .filter(b -> b.getJegy().isPresent())
                .filter(b -> b.getJegy().get().getErtek() >= 5)
                .count();
        return (double) atment / ertekelt * 100.0;
    }

    @Override
    public String toString() {
        return "Kurzus{nev='" + nev + "', oktato='" + oktato.getNev()
                + "', kredit=" + kredit + ", maxLetszam=" + maxLetszam + "}";
    }
}
