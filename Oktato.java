package tanulmanyi;

public class Oktato extends Szemely {
    public Oktato(String azonosito, String nev, String email) {
        super(azonosito, nev, email);
    }

    @Override
    public String toString() {
        return "Oktato{azonosito='" + getAzonosito() + "', nev='" + getNev()
                + "', email='" + getEmail() + "'}";
    }
}
