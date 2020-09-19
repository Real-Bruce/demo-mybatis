package cn.bruce.dao;

import cn.bruce.domain.User;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-14 8:53
 */
public interface IUserDao {
    /**
     * 获取全部用户
     * @return
     */
    List<User> getUsers();

    /**
     * 通过id获取用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 通过名称获取用户
     * @param userName
     * @return
     */
    List<User> getUserByName(String userName);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUserById(Integer userId);

    /**
     * 获取用户数量
     * @return
     */
    int getTotalUser();
}
