package cn.bruce.mybatis.sqlsession;

/**
 * @author Bruce
 * @create 2020-09-10 14:28
 * 自定义mybatis中和数据库交互的核心类，可以创建dao接口代理对象
 */
public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
