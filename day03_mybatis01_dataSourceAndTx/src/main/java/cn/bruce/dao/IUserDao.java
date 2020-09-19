package cn.bruce.dao;

import cn.bruce.domain.QueryVo;
import cn.bruce.domain.User;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-14 15:42
 */
public interface IUserDao {

    /**
     * 增加新的用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUserById(Integer userId);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 获取全部用户
     * @return
     */
    List<User> getUsers();

    /**
     * 根据id查询用户
     * @return
     * @param i
     */
    User getUserById(int i);

    /**
     * 根据名称模糊查询
     * @param userName
     * @return
     */
    List<User> getUserByName(String userName);

    /**
     * 获取用户总数
     * @return
     */
    int getTotalUser();

    /**
     * 根据queryVo查询用户
     * @param vo
     * @return
     */
    List<User> getUserByQueryVo(QueryVo vo);
}
