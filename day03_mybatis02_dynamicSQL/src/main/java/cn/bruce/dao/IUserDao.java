package cn.bruce.dao;

import cn.bruce.domain.QueryVo;
import cn.bruce.domain.User;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-15 9:33
 */
public interface IUserDao {

    /**
     * 新增
     * @param user
     */
    void saveUser(User user);

    /**
     * 删除
     * @param userId
     */
    void deleteUserById(Integer userId);

    /**
     * 修改
     * @param user
     */
    void updateUser(User user);

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
     * 根据名称模糊查询获取用户
     * @param userName
     * @return
     */
    List<User> getUserByName(String userName);

    /**
     * 根据QueryVo获取用户
     * @param vo
     * @return
     */
    List<User> getUserByQueryVo(QueryVo vo);

    /**
     * 多条件查询
     * @param user
     * @return
     */
    List<User> getUserByCondition(User user);

    /**
     * 根据id集合查询
     * @param vo
     * @return
     */
    List<User> getUserByIds(QueryVo vo);
}
