package ru.relex.hotelteam.db.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface IUserMapper {

    User createUser(User user);

    Optional<User> getById(int id);

    List<User> listUsers();

    void deleteUser(@Param("id") int id);

    void updateUser(User user);
}