package cn.bruce.dao;

import cn.bruce.domain.User;
import cn.bruce.mybatis.annotation.Select;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-11 10:35
 */
public interface IUserDao {

    /**
     * 获取全部的user对象
     * @return
     */
    @Select("select * from user")
    List<User> getUsers();

    /**
     * 通过id获取用户
     * @return
     */
    User getUserById();
}
