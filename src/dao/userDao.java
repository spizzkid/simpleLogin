package dao;

import pojo.user;

public interface userDao {
    int add(user user);

    user selectUser(String username);

    int update (user user);
}
