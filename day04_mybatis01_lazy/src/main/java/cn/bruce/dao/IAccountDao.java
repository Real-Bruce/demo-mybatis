package cn.bruce.dao;

import cn.bruce.domain.Account;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-16 15:51
 */
public interface IAccountDao {

    /**
     * 获取全部账户信息
     * @return
     */
    List<Account> getAccounts();

    /**
     * 根据user的id查询账户
     * @param userId
     * @return
     */
    Account getAccountByUid(Integer userId);

}
