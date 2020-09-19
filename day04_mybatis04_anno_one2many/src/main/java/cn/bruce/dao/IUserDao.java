package cn.bruce.dao;

import cn.bruce.test.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-18 8:56
 * mybatis CRUD注解 @Select @Insert @Update @Delete
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap", value={
            @Result(id = true, property = "userId",column = "id"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userBirthday", column = "birthday"),
            @Result(property = "userSex", column = "sex"),
            @Result(property = "userAddress", column = "address"),
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "cn.bruce.dao.IAccountDao.getAccountByUid", fetchType = FetchType.LAZY))
    })
    List<User> getUsers();

    /**
     * 根据id获取用户
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{userId}")
    @ResultMap("userMap")
    User getUserById(Integer userId);

    /**
     * 根据名称进行模糊查询
     * @param userName
     * @return
     */
    @Select("select * from user where username like '%${userName}%'")
    @ResultMap("userMap")
    List<User> getUserByName(String userName);

}
