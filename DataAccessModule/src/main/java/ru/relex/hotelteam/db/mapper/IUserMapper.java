package ru.relex.hotelteam.db.mapper;


import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.User;
import ru.relex.hotelteam.shared.model.Authority;
import ru.relex.hotelteam.shared.model.CurrentUser;

@Mapper
public interface IUserMapper {

  User createUser(User user);

  Optional<User> getUserById(int id);

  Optional<User> getUserByLogin(@Param("login") String login);

  List<User> listUsers();

  List<User> getCurrentGuests();

  void deleteUser(@Param("id") int id);

  void updateUser(User user);

  void updateUserSecurityInfo(User user);

  Optional<CurrentUser> getCurrentUser(String principal);

  Authority getAuthoritiesForUser(String login);
}
