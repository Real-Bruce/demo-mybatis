package cn.bruce.dao;

import cn.bruce.domain.User;

import java.io.InputStream;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-17 10:28
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

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);
}
