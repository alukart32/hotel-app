package ru.relex.hotelteam.shared.model;

public enum Authorities {

    OWNER(1),
    ADMIN(2),
    GUEST(3);

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
