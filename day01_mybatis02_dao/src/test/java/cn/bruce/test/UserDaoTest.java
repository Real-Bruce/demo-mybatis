package cn.bruce.test;

import cn.bruce.dao.IUserDao;
import cn.bruce.dao.impl.UserDaoImpl;
import cn.bruce.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-10 10:21
 */
public class UserDaoTest {

    public static void main(String[] args)throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂创建dao对象
        IUserDao userDao = new UserDaoImpl(factory);
        //4.使用代理对象执行方法
        List<User> users = userDao.getUsers();
        for(User user : users){
            System.out.println(user);
        }
        //5.释放资源
        in.close();
    }
}
