package ru.relex.hotelteam.shared.model;

public enum Authority {

    OWNER(1),
    ADMIN(2),
    GUEST(3);

    private final int id;

    Authority(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Authority of(Integer id) {
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
