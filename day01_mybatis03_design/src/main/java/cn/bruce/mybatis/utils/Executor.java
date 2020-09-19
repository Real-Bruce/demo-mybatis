package cn.bruce.mybatis.utils;

import cn.bruce.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-11 9:29
 */
public class Executor {

    public <E> List<E> selectList(Mapper mapper, Connection conn) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            // 1.取出mapper中的数据
            String queryString = mapper.getQueryString();
            String resultType = mapper.getResultType();
            Class domainClass = Class.forName(resultType);
            // 2.获取PrepareStatement对象
            pstm = conn.prepareStatement(queryString);
            // 3.执行sql语句，获取结果集
            rs = pstm.executeQuery();
            // 4.封装结果集
            List<E> list = new ArrayList<E>();
            while (rs.next()) {
                // 实例化要封装的实体类对象
                E obj = (E) domainClass.newInstance();
                // 取出结果集的元信息:ResultSetMetaData
                ResultSetMetaData rsmd = rs.getMetaData();
                // 取出总列数
                int columnCount = rsmd.getColumnCount();
                // 遍历总列数
                for (int i = 1; i <= columnCount ; i++) {
                    // 获取每列的名称，列名和序号是从1开始的
                    String columnName = rsmd.getColumnName(i);
                    // 根据列名获取每列的值
                    Object columnValue = rs.getObject(columnName);
                    // 给obj赋值：通过java内省机制（借助PropertyDescriptor实现属性封装）
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, domainClass);
                    // 获取写入方法
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    // 获取列的值，给对象赋值
                    writeMethod.invoke(obj, columnValue);
                }
                // 将赋值好的对象加入到集合中
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release (pstm, rs);
        }

    }

    private void release(PreparedStatement pstm, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
