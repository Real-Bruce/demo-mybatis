package cn.bruce.mybatis.sqlsession.proxy;

import cn.bruce.mybatis.cfg.Mapper;
import cn.bruce.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author Bruce
 * @create 2020-09-11 9:16
 */
public class MapperProxy implements InvocationHandler {

    /**
     * map中key是全限定类名+方法名
     */
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
     * 对方法进行增强，即调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 1.获取方法名
        String methodName = method.getName();

        // 2.获取方法所在类的名称
        String className = method.getDeclaringClass().getName();

        // 3.组合key
        String key = className + "." + methodName;

        // 4.获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);

        // 5.判断是否有mapper
        if (mapper == null) {
            throw new  IllegalAccessException("传入的参数异常");
        }

        // 6.调用工具执行查询操作
        return new Executor().selectList(mapper, conn);
    }
}
