package cn.bruce.dao;

import cn.bruce.domain.Account;
import cn.bruce.domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-15 16:44
 */
public class IAccountDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

    /**
     * 查询全部账户和对应的用户
     */
    @Test
    public void testGetAccounts(){
        List<Account> accounts = accountDao.getAccounts();
        for (Account a : accounts) {
            System.out.println("---------------------");
            System.out.println(a);
            System.out.println(a.getUser());
        }
    }

    @Test
    public void testGetUsersWithMore(){
        List<AccountUser> accounts = accountDao.getAccountWithMore();
        for (Account at : accounts) {
            System.out.println(at);
        }
    }

}
