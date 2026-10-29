package tanulmanyi;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lekerdezesek {
    public static List<Hallgato> legjobbHarom(Kurzus kurzus) {
        return kurzus.getBeiratkozasok().stream()
                .filter(b -> b.getJegy().isPresent())
                .sorted(Comparator.comparingInt(
                        (Beiratkozas b) -> b.getJegy().get().getErtek()).reversed())
                .limit(3)
                .map(Beiratkozas::getHallgato)
                .collect(Collectors.toList());
    }

    public static Map<Oktato, Integer> oktatonkentiKreditek(List<Kurzus> kurzusok) {
        return kurzusok.stream()
                .collect(Collectors.groupingBy(
                        Kurzus::getOktato,
                        Collectors.summingInt(Kurzus::getKredit)));
    }
}
