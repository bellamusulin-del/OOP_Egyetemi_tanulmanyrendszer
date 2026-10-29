package tanulmanyi;

import java.util.Optional;

public class Beiratkozas {
    private final Hallgato hallgato;
    private final Kurzus kurzus;
    private final String felev;
    private Jegy jegy;

    Beiratkozas(Hallgato hallgato, Kurzus kurzus, String felev) {
        this.hallgato = hallgato;
        this.kurzus = kurzus;
        this.felev = felev;
    }

    public Hallgato getHallgato() {
        return hallgato;
    }

    public Kurzus getKurzus() {
        return kurzus;
    }

    public String getFelev() {
        return felev;
    }

    public Optional<Jegy> getJegy() {
        return Optional.ofNullable(jegy);
    }

    public void setJegy(Jegy jegy) {
        if (jegy == null) {
            throw new IllegalArgumentException("A jegy nem lehet null.");
        }
        this.jegy = jegy;
    }

    @Override
    public String toString() {
        return "Beiratkozas{hallgato='" + hallgato.getNev() + "', kurzus='"
                + kurzus.getNev() + "', felev='" + felev + "', jegy=" + getJegy() + "}";
    }
}
