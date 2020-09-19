package cn.bruce.dao;

import cn.bruce.domain.User;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-10 9:46
 */
public interface IUserDao {

    List<User> getUsers();
}
