package ru.relex.hotelteam.shared.model;

public enum Authorities {

    ADMIN(1),
    USER(2)
    ;

    private final int id;

    Authorities(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Authorities of(Integer id) {
        if (id == null) {
            return null;
        }

        for (var value: values()) {
            if (value.id == id) {
                return value;
            }
        }

        return null;
    }
}