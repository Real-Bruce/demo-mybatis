package cn.bruce.dao;

import cn.bruce.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-17 14:37
 */
public interface IUserDao {

    /**
     * 增加新的用户
     * @param user
     */
    @Insert("insert into user (username, birthday, sex, address) values (#{username}, #{birthday}, #{sex}, #{address})")
    void saveUser(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    @Delete("delete from user where id = #{userId}")
    void deleteUserById(Integer userId);

    /**
     * 修改用户信息
     * @param user
     */
    @Update("update user set username = #{username}, birthday = #{birthday}, sex = #{sex}, address = #{address} where" +
            " id = #{id}")
    void updateUser(User user);

    /**
     * 获取全部用户
     * @return
     */
    @Select("select * from user")
    List<User> getUsers();

    /**
     * 根据id获取用户
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{userId}")
    User getUserById(Integer userId);

    /**
     * 根据名称模糊查询
     * @param userName
     * @return
     */
    @Select("select * from user where username like '%${userName}%'")
    List<User> getUserByName(String userName);

    /**
     * 查询用户总数
     * @return
     */
    @Select("select count(*) from user")
    int getTotalUser();

}
