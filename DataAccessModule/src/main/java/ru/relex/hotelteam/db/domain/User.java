package ru.relex.hotelteam.db.domain;


import lombok.Getter;
import lombok.Setter;
import ru.relex.hotelteam.shared.model.Authorities;

@Getter
@Setter
public class User {

    private int id;
    private String password;
    private String login;
    private String firstName;
    private String lastName;
    private String middleName;
    private Authorities authorities;

    public User(int id, String password, String login,
                String firstName, String lastName, String middleName, Authorities authorities) {
        this.id = id;
        this.password = password;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.authorities = authorities;
    }
}

