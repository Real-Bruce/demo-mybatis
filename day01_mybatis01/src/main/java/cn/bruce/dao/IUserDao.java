package cn.bruce.dao;

import cn.bruce.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-09 16:08
 */
public interface IUserDao {
    /**
     * 获取全部的用户
     * @return 用户集合
     */
    @Select("select * from user")
    List<User> getUsers();
}