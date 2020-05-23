package pl.grsrpg.utils;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum Attribute {
    MAXHEALTH(4),
    STRENGTH(1),
    AGILITY(2),
    MAGICPOINTS(3);

    private static final Map<Integer, Attribute> LOOKUP = stream(values()).collect(toMap(Attribute::getId, x -> x));

    private final int id;

    Attribute(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Attribute fromId(int id) {
        return LOOKUP.get(id);
    }
}
