package cn.bruce.mybatis.utils;

import cn.bruce.mybatis.annotation.Select;
import cn.bruce.mybatis.cfg.Configuration;
import cn.bruce.mybatis.cfg.Mapper;
import cn.bruce.mybatis.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bruce
 * @create 2020-09-10 14:42
 * 用于解析配置文件
 */
public class XmlConfigBuilder {

    public static Configuration loadConfiguration(InputStream config) {

        try {
            // 定义封装连接信息配置对象（mybatis配置对象）
            Configuration cfg = new Configuration();
            // 1.获取SAXReader对象
            SAXReader reader = new SAXReader();
            // 2.根据字节流输入获取Document对象
            Document document = reader.read(config);
            // 3.获取根节点
            Element root = document.getRootElement();
            // 4.使用xpath中选择节点的方式，获取所有的property节点
            List <Element> propertyElements = root.selectNodes("//property");
            // 5.遍历节点
            for (Element propertyElement : propertyElements) {
                // 判断数据库连接部分，并取出name属性
                String name = propertyElement.attributeValue("name");

                if ("driver".equals(name)) {
                    // 获取property标签的value属性
                    String driver = propertyElement.attributeValue("value");
                    cfg.setDriver(driver);
                }

                if ("url".equals(name)) {
                    // 获取property标签的value属性
                    String url = propertyElement.attributeValue("value");
                    cfg.setUrl(url);
                }

                if ("username".equals(name)) {
                    // 获取property标签的value属性
                    String username = propertyElement.attributeValue("value");
                    cfg.setUsername(username);
                }

                if ("password".equals(name)) {
                    // 获取property标签的value属性
                    String password = propertyElement.attributeValue("value");
                    cfg.setPassword(password);
                }
            }

            // 取出mappers中所有的mapper标签，判断他们使用了resource还是class属性
            List <Element> mapperElements = root.selectNodes("//mappers//mapper");
            // 遍历集合
            for (Element mapperElement : mapperElements) {
                // 判断mapperElement使用的是哪个属性
                Attribute attribute = mapperElement.attribute("resource");

                if (attribute != null) {
                    System.out.println("使用的是XML配置。。。");
                    // 有resource属性，用的时xml，并取出属性的值
                    String mapperPath = attribute.getValue();
                    // 将映射配置文件的内容取出来，封装成map
                    Map<String, Mapper> mappers = loadMapperConfiguration(mapperPath);
                    // 给configuration中的mappers赋值
                    cfg.setMappers(mappers);
                } else {
                    System.out.println("使用的是注解配置。。。");
                    // 没有resource属性，使用的是注解，取出class的值
                    String daoClassPath = mapperElement.attributeValue("class");
                    // 根据daoClassPath获取封装的必要信息
                    Map<String, Mapper> mappers = loadMapperAnnotation(daoClassPath);
                    // 给configuration中的mappers赋值
                    cfg.setMappers(mappers);
                }
            }
            // 返回configuration
            return cfg;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                config.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据传入的参数，解析xml并封装到Map中
     * @param mapperPath 映射配置文件的位置
     * @return map中包含了获取唯一标识（key由dao的全限定类名和方法名组成）
     *            和执行所需的必要信息（value是一个Mapper对象，用于存放执行的SQL语句和要封装的实体类的全限定类名）
     */
    private static Map<String, Mapper> loadMapperConfiguration(String mapperPath) throws IOException {

        InputStream resource = null;

        try {
            // 定义返回值对象
            Map<String, Mapper> mappers = new HashMap<String, Mapper>();
            // 1.根据路径获取字节输入流
            resource = Resources.getResourceAsStream(mapperPath);
            // 2.根据字节输入流获取Document对象
            SAXReader reader = new SAXReader();
            Document document = reader.read(resource);
            // 3.获取根节点
            Element root = document.getRootElement();
            // 4.获取根节点的namespace属性取值
            String namespace = root.attributeValue("namespace");
            // 5.获取所有select节点
            List <Element> selectElements = root.selectNodes("//select");
            // 6.遍历select节点集合
            for (Element selectElement : selectElements) {
                // 取出id属性          组成map中的key部分
                String id = selectElement.attributeValue("id");
                // 取出resultType属性  组成map中的value部分
                String resultType = selectElement.attributeValue("resultType");
                // 取出文本内容         组成map中的value部分
                String queryString = selectElement.getText();
                // 创建key
                String key = namespace + "." + id;
                // 创建value
                Mapper mapper = new Mapper();
                mapper.setQueryString(queryString);
                mapper.setResultType(resultType);
                // 将key和value存入到mappers中
                mappers.put(key, mapper);
            }
            return mappers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            resource.close();
        }
    }

    /**
     * 根据传入的参数，得到dao中所有被select注解标注的方法
     * 根据方法名称和类名，以及方法上注解的value属性的值，组成Mapper的必要信息
     * @param daoClassPath
     * @return
     */
    private static Map<String, Mapper> loadMapperAnnotation(String daoClassPath) throws Exception {
        // 定义返回值对象
        Map<String, Mapper> mappers = new HashMap<String, Mapper>();
        // 1.得到dao接口的字节码对象
        Class daoClass = Class.forName(daoClassPath);
        // 2. 得到到接口的方法数组
        Method[] methods = daoClass.getMethods();
        // 3.遍历Method数组
        for (Method method : methods){
            // 取出每个方法判断是否有select注解
            boolean isAnnotated = method.isAnnotationPresent(Select.class);
            if (isAnnotated) {
                // 创建mapper对象
                Mapper mapper = new Mapper();
                // 取出注解的value属性值
                Select selectAnnotation = method.getAnnotation(Select.class);
                String queryString = selectAnnotation.value();
                mapper.setQueryString(queryString);
                // 获取当前方法的返回值，要求带有泛型信息
                Type genericReturnType = method.getGenericReturnType();
                // 判断type是不是参数化类型
                if (genericReturnType instanceof ParameterizedType) {
                    // 强转
                    ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
                    // 得到参数类型中的实际类型参数
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    // 取出第一个参数
                    Class domainClass = (Class) actualTypeArguments[0];
                    // 获取domainClass的类名
                    String resultType = domainClass.getName();
                    // 给Mapper赋值
                    mapper.setResultType(resultType);
                }
                // 组装key的信息获取方法名称
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String key = className + "." + methodName;
                // 给map赋值
                mappers.put(key, mapper);
            }
        }
        return mappers;
    }
}
