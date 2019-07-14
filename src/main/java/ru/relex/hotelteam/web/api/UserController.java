package ru.relex.hotelteam.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.hotelteam.service.IUserService;
import ru.relex.hotelteam.service.dto.UserSafeDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;

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
    public UserSafeDTO createUser(@RequestBody UserSafeDTO dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserSafeDTO> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public UserSafeDTO findById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeUser(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserUpdateDTO updateUser(@PathVariable("id") int id, @RequestBody UserUpdateDTO dto){ return userService.update(id, dto); }
}