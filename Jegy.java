package tanulmanyi;

public class Jegy {
    private final int ertek;

    public Jegy(int ertek) {
        if (ertek < 1 || ertek > 10) {
            throw new IllegalArgumentException("A jegy csak 1 es 10 kozotti egesz lehet.");
        }
        this.ertek = ertek;
    }

    public int getErtek() {
        return ertek;
    }

    @Override
    public String toString() {
        return "Jegy{ertek=" + ertek + "}";
    }
}
