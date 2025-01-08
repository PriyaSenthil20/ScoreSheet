package com.game.scoresheet.dao;

import com.game.scoresheet.model.RegisterUserDto;
import com.game.scoresheet.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
