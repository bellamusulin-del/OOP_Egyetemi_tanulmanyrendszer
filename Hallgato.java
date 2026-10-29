package tanulmanyi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hallgato extends Szemely {
    private final List<Beiratkozas> beiratkozasok = new ArrayList<>();

    public Hallgato(String azonosito, String nev, String email) {
        super(azonosito, nev, email);
    }

    public List<Beiratkozas> getBeiratkozasok() {
        return List.copyOf(beiratkozasok);
    }

    public Beiratkozas beiratkozik(Kurzus kurzus, String felev) {
        if (kurzus == null || felev == null || felev.isBlank()) {
            throw new IllegalArgumentException("A kurzus es a felev kotelezo.");
        }
        if (kurzus.getBeiratkozasok().stream().anyMatch(
                b -> b.getHallgato().equals(this) && b.getFelev().equals(felev))) {
            throw new IllegalArgumentException("Erre a kurzusra ebben a felevben mar beiratkozott.");
        }
        if (kurzus.getBeiratkozasok().size() >= kurzus.getMaxLetszam()) {
            throw new IllegalStateException("A kurzus megtelt.");
        }

        Beiratkozas beiratkozas = new Beiratkozas(this, kurzus, felev);
        beiratkozasok.add(beiratkozas);
        kurzus.hozzaadBeiratkozas(beiratkozas);
        return beiratkozas;
    }

    public double getSulyozottAtlag() {
        double osszeg = 0;
        int kreditek = 0;

        for (Beiratkozas beiratkozas : beiratkozasok) {
            Optional<Jegy> jegy = beiratkozas.getJegy();
            if (jegy.isPresent()) {
                int kredit = beiratkozas.getKurzus().getKredit();
                osszeg += jegy.get().getErtek() * kredit;
                kreditek += kredit;
            }
        }

        if (kreditek == 0) {
            return 0.0;
        }
        return osszeg / kreditek;
    }

    public int getMegszerezettKreditek() {
        int osszeg = 0;
        for (Beiratkozas beiratkozas : beiratkozasok) {
            if (beiratkozas.getJegy().isPresent()
                    && beiratkozas.getJegy().get().getErtek() >= 5) {
                osszeg += beiratkozas.getKurzus().getKredit();
            }
        }
        return osszeg;
    }

    @Override
    public String toString() {
        return "Hallgato{azonosito='" + getAzonosito() + "', nev='" + getNev()
                + "', email='" + getEmail() + "', beiratkozasok=" + beiratkozasok.size() + "}";
    }
}
