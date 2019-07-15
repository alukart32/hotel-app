package ru.relex.hotelteam.service.dto.domain;

import ru.relex.hotelteam.shared.model.Authority;

/**
 * UserAuthDTO - представляет непосредственные данные безопасности
 * конкретного user
 */
public class UserAuthDTO {

    private String login;
    private String email;
    private Authority authority;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}