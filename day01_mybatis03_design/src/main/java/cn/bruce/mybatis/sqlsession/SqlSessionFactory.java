package cn.bruce.mybatis.sqlsession;

/**
 * @author Bruce
 * @create 2020-09-10 14:36
 */
public interface SqlSessionFactory {

    /**
     * 打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
