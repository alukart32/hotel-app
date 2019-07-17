package ru.relex.hotelteam.web.api;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.relex.hotelteam.service.IUserService;
import ru.relex.hotelteam.service.dto.UserDTO;
import ru.relex.hotelteam.service.dto.UserUpdateDTO;

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
  @ResponseStatus(HttpStatus.OK)
  public void removeUser(@PathVariable("id") int id) {
    userService.delete(id);
  }

  @PutMapping("/{id}")
  public void updateUser(@PathVariable("id") int id, @RequestBody UserUpdateDTO dto) {
    userService.update(id, dto);
  }
}
