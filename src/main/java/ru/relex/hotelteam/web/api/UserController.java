package ru.relex.hotelteam.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.hotelteam.service.IUserService;
import ru.relex.hotelteam.service.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping(path = "/users",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private final IUserService userService;

    public UserController(final IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserDTO> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") int id) {
        userService.delete(id);
    }
}