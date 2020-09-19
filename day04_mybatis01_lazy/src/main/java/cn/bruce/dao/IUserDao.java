package cn.bruce.dao;

import cn.bruce.domain.User;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-16 15:46
 */
public interface IUserDao {

    /**
     * 获取全部用户
     * @return
     */
    List<User> getUsers();

    /**
     * 根据id获取用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);
}
