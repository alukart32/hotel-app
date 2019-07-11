package ru.relex.hotelteam.service.dto;

import lombok.Getter;
import lombok.Setter;
import ru.relex.hotelteam.shared.model.Authorities;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String password;
    private String login;
    private String firstName;
    private String lastName;
    private String middleName;
    private Authorities role;

    public UserDTO(int id, String password, String login,
                   String firstName, String lastName, String middleName, Authorities role) {
        this.id = id;
        this.password = password;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.role = role;
    }
}
