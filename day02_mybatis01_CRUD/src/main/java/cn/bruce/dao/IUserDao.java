package cn.bruce.dao;

import cn.bruce.domain.QueryVo;
import cn.bruce.domain.User;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-11 16:08
 */
public interface IUserDao {

    /**
     * 获取所有的账户
     * @return
     */
    List<User> getUsers();

    /**
     * 保存账户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新账户
     * @param user
     */
    void updateUserById(User user);

    /**
     * 通过id查询用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 通过id删除用户
     * @param userId
     */
    void deleteUserById(Integer userId);

    /**
     * 通过名称查询用户
     * @param name
     * @return
     */
    List<User> getUserByName(String name);

    /**
     * 查询用户总数
     * @return
     */
    int getUserTotal();

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> getUserByQueryVo(QueryVo vo);
}
