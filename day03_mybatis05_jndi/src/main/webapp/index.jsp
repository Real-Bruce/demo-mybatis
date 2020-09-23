<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="cn.bruce.dao.IUserDao" %>
<%@ page import="cn.bruce.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mybatis05_jndi</title>
</head>
<body>
    <h2>Test JNDI</h2>
    <%
        // 1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.根据配置文件构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用SqlSessionFactory创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 4.使用SqlSession对象构建Dao代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 5.执行dao中的findAll方法
        List<User> users = userDao.getUsers();
        for (User user : users) {
            System.out.println(user);
        }
        // 6.释放资源
        sqlSession.close();
        in.close();
    %>
</body>
</html>
