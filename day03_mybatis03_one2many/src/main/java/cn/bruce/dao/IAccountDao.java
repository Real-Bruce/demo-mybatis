package cn.bruce.dao;

import cn.bruce.domain.Account;
import cn.bruce.domain.AccountUser;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-15 14:30
 */
public interface IAccountDao {

    /**
     * 获取全部账户
     * @return
     */
    List<Account> getAccounts();

    /**
     * 获取用户账户带有更多信息
     * @return
     */
    List<AccountUser> getAccountWithMore();
}
