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
import ru.relex.hotelteam.service.dto.UserBaseDto;
import ru.relex.hotelteam.service.dto.UserDto;
import ru.relex.hotelteam.service.dto.UserSecurityDto;
import ru.relex.hotelteam.service.dto.UserUpdateDto;

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
  public UserBaseDto createUser(@RequestBody UserDto dto) {
    return userService.createUser(dto);
  }

  @GetMapping
  public List<UserBaseDto> listUsers() {
    return userService.listUsers();
  }

  @GetMapping("/{id}")
  public UserBaseDto findById(@PathVariable("id") int id) {
    return userService.findById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void removeUser(@PathVariable("id") int id) {
    userService.delete(id);
  }

  @PutMapping("/{id}")
  public void updateUser(@PathVariable("id") int id, @RequestBody UserUpdateDto dto) {
    userService.update(id, dto);
  }

  @PutMapping("/security/{id}")
  public void updateUserSecurityInfo(@PathVariable("id") int id, @RequestBody UserSecurityDto dto) {
    userService.updateSecurityInfo(id, dto);
  }

}
