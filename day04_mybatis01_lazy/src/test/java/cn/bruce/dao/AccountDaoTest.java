package cn.bruce.dao;

import cn.bruce.domain.Account;
import cn.bruce.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-16 16:26
 */
public class AccountDaoTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSession = new SqlSessionFactoryBuilder().build(in).openSession();
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

    @Test
    public void testGetUsers(){
        List<Account> accounts = accountDao.getAccounts();
        for (Account a : accounts) {
            System.out.println("--------------");
            System.out.println(a);
            System.out.println(a.getUser());
        }
    }
}
