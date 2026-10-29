package tanulmanyi;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class TanulmanyiRendszerTeszt {
    private Oktato oktato() {
        return new Oktato("O1", "Kiss Anna", "anna@example.com");
    }

    @Test
    void megteltKurzusraNemLehetBeiratkozni() {
        Kurzus kurzus = new Kurzus("Java", oktato(), 5, 1);
        new Hallgato("H1", "Nagy Eva", "eva@example.com").beiratkozik(kurzus, "2026-osz");
        Hallgato masik = new Hallgato("H2", "Toth Bela", "bela@example.com");
        assertThrows(IllegalStateException.class, () -> masik.beiratkozik(kurzus, "2026-osz"));
    }

    @Test
    void ugyanarraAKurzusraEgyFelevbenCsakEgyszerLehet() {
        Kurzus kurzus = new Kurzus("Java", oktato(), 5, 10);
        Hallgato hallgato = new Hallgato("H1", "Nagy Eva", "eva@example.com");
        hallgato.beiratkozik(kurzus, "2026-osz");
        assertThrows(IllegalArgumentException.class, () -> hallgato.beiratkozik(kurzus, "2026-osz"));
    }

    @Test
    void jegyCsakEgyEsTizKozottLehet() {
        assertThrows(IllegalArgumentException.class, () -> new Jegy(0));
        assertThrows(IllegalArgumentException.class, () -> new Jegy(11));
        assertEquals(10, new Jegy(10).getErtek());
    }

    @Test
    void sulyozottAtlagKreditSzerintSzamol() {
        Hallgato hallgato = new Hallgato("H1", "Nagy Eva", "eva@example.com");
        Beiratkozas elso = hallgato.beiratkozik(new Kurzus("Java", oktato(), 5, 10), "2026-osz");
        Beiratkozas masodik = hallgato.beiratkozik(new Kurzus("SQL", oktato(), 3, 10), "2026-osz");
        elso.setJegy(new Jegy(8));
        masodik.setJegy(new Jegy(4));
        assertEquals(6.5, hallgato.getSulyozottAtlag(), 0.001);
    }

    @Test
    void csakAzAtmentKurzusKreditjeSzamit() {
        Hallgato hallgato = new Hallgato("H1", "Nagy Eva", "eva@example.com");
        Beiratkozas elso = hallgato.beiratkozik(new Kurzus("Java", oktato(), 5, 10), "2026-osz");
        Beiratkozas masodik = hallgato.beiratkozik(new Kurzus("SQL", oktato(), 3, 10), "2026-osz");
        elso.setJegy(new Jegy(5));
        masodik.setJegy(new Jegy(4));
        assertEquals(5, hallgato.getMegszerezettKreditek());
    }

    @Test
    void atmenesiAranyCsakAzErtekeltBeiratkozasokatNezi() {
        Kurzus kurzus = new Kurzus("Java", oktato(), 5, 10);
        Hallgato egyik = new Hallgato("H1", "Nagy Eva", "eva@example.com");
        Hallgato masik = new Hallgato("H2", "Toth Bela", "bela@example.com");
        egyik.beiratkozik(kurzus, "2026-osz").setJegy(new Jegy(7));
        masik.beiratkozik(kurzus, "2026-osz").setJegy(new Jegy(3));
        assertEquals(50.0, kurzus.getAtmenesiArany(), 0.001);
    }

    @Test
    void szemelyEgyenlosegAzonositoAlapjanMukodik() {
        Hallgato egyik = new Hallgato("H1", "Nagy Eva", "eva@example.com");
        Hallgato masik = new Hallgato("H1", "Masik Nev", "masik@example.com");
        assertEquals(egyik, masik);
        assertEquals(egyik.hashCode(), masik.hashCode());
    }

    @Test
    void streamLekerdezesekMukodnek() {
        Oktato oktato = oktato();
        Kurzus kurzus = new Kurzus("Java", oktato, 5, 10);
        Hallgato egyik = new Hallgato("H1", "Nagy Eva", "eva@example.com");
        Hallgato masik = new Hallgato("H2", "Toth Bela", "bela@example.com");
        egyik.beiratkozik(kurzus, "2026-osz").setJegy(new Jegy(9));
        masik.beiratkozik(kurzus, "2026-osz").setJegy(new Jegy(6));
        assertEquals(egyik, Lekerdezesek.legjobbHarom(kurzus).get(0));
        Map<Oktato, Integer> eredmeny = Lekerdezesek.oktatonkentiKreditek(List.of(kurzus));
        assertEquals(5, eredmeny.get(oktato));
    }
}
