package cn.bruce.dao;

import cn.bruce.domain.QueryVo;
import cn.bruce.domain.User;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-21 9:25
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
    void deleteUser(Integer userId);

    /**
     * 修改
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询全部
     * @return
     */
    List<User> getUsers();

    /**
     * 根据id查询
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 根据名称模糊查询
     * @param userName
     * @return
     */
    List<User> getUserByName(String userName);

    /**
     * 根据queryVo查询
     * @param vo
     * @return
     */
    List<User> getUserByQueryVo(QueryVo vo);

    /**
     * 获取用户总数
     * @return
     */
    int getTotalUser();
}
