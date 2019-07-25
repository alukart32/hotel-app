package ru.relex.hotelteam.db.mapper;


import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.User;

@Mapper
public interface IUserMapper {

  User createUser(User user);

  Optional<User> getUserById(int id);

  Optional<User> getUserByLogin(@Param("login") String login);

  List<User> listUsers();

  void deleteUser(@Param("id") int id);

  void updateUser(User user);

  void updateUserSecurityInfo(User user);
}
