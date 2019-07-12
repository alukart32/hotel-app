package ru.relex.hotelteam.db.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.User;

import java.util.List;

@Mapper
public interface IUserMapper {

    User createUser(User user);

    User getUserById(int id);

    List<User> listUsers();

    void deleteUser(@Param("id") int id);
}