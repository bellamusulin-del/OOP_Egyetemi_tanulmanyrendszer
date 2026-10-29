package tanulmanyi;

import java.util.Objects;

public abstract class Szemely {
    private final String azonosito;
    private String nev;
    private String email;

    protected Szemely(String azonosito, String nev, String email) {
        if (azonosito == null || azonosito.isBlank()) {
            throw new IllegalArgumentException("Az azonosito nem lehet ures.");
        }
        if (nev == null || nev.isBlank()) {
            throw new IllegalArgumentException("A nev nem lehet ures.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Az email nem lehet ures.");
        }
        this.azonosito = azonosito;
        this.nev = nev;
        this.email = email;
    }

    public String getAzonosito() {
        return azonosito;
    }

    public String getNev() {
        return nev;
    }

    public String getEmail() {
        return email;
    }

    public void setNev(String nev) {
        if (nev == null || nev.isBlank()) {
            throw new IllegalArgumentException("A nev nem lehet ures.");
        }
        this.nev = nev;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Az email nem lehet ures.");
        }
        this.email = email;
    }

    @Override
    public boolean equals(Object objektum) {
        if (this == objektum) {
            return true;
        }
        if (!(objektum instanceof Szemely)) {
            return false;
        }
        Szemely szemely = (Szemely) objektum;
        return azonosito.equals(szemely.azonosito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(azonosito);
    }

    @Override
    public String toString() {
        return "Szemely{azonosito='" + azonosito + "', nev='" + nev
                + "', email='" + email + "'}";
    }
}
