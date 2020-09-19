package cn.bruce.dao;

import cn.bruce.test.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-18 9:16
 */
public interface IAccountDao {

    /**
     * 获取全部账户
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            @Result(property = "user", column = "uid",
                    one = @One(select = "cn.bruce.dao.IUserDao.getUserById", fetchType = FetchType.EAGER))
    })
    List<Account> getAccounts();

    /**
     * 根据uid获取账户信息
     * @param userId
     * @return
     */
    @Select("select * from account where uid = #{userId}")
    List<Account> getAccountByUid(Integer userId);
}
