package ru.relex.hotelteam.db.domain;


import ru.relex.hotelteam.shared.model.Authorities;


public class User {

    private int id;
    private String password;
    private String login;
    private String firstName;
    private String lastName;
    private String middleName;
    private Authorities authority;

    public User() {}

    public User(int id, String password, String login,
                String firstName, String lastName, String middleName, Authorities authority) {
        this.id = id;
        this.password = password;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Authorities getAuthority() {
        return authority;
    }

    public void setAuthority(Authorities authority) {
        this.authority = authority;
    }
}

